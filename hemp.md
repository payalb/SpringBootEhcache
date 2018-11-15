Ehcache is one of the most popular caching implementation available as the open source caching implementation. This is located under the ackage org.springframework.cache.ehcache. You have to declare appropriate cache manager to start using it in your application.
The cache manager configuration for ehcache is:
<bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager" p:cache-manager-ref="ehcache"/>

<!-- EhCache library setup -->
<bean id="ehcache" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean" p:config-location="ehcache.xml"/>
If you want to configure using the Java configuration class, add the following lines of code:
@Bean
	public CacheManager ehCacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}
@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
Note that you should have ehcache.xml in the classpath or any other location where your spring application can load without any issues. This configuration provides more details about the cache size, file name, etc.
Here is the example of the ehcache.xml file:
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="ehcache.xsd" updateCheck="true" monitoring="autodetect" dynamicConfig="true">

	<diskStore path="java.io.tmpdir" />
	
	<cache name="movieFindCache" maxEntriesLocalHeap="10000" maxEntriesLocalDisk="1000" eternal="false" diskSpoolBufferSizeMB="20" timeToIdleSeconds="300" timeToLiveSeconds="600" memoryStoreEvictionPolicy="LFU" transactionalMode="off">
		<persistence strategy="localTempSwap" />
	</cache>

</ehcache>
	
	
	Cache Fallback Mechanism
There are situations when we change to different environments or any other scenarios our applications may not have caching store configured. In this case a runtime exception will be thrown by the spring application due to failure on finding the suitable cache store. Instead of removing the caching declarations in the application (which is teadious and time consuming work), we can configure a fall bacl dummy cache store to avoid and exception thrown. What happens is, when there is no cache store found by the spring application, it just executes the method normally without enforcing any caching mechanism. The declaration for fall back cache would look like this:
<bean id="cacheManager" class="org.springframework.cache.support.CompositeCacheManager">
    <property name="cacheManagers">
        <list>
            <ref bean="jdkCache"/>
            <ref bean="ehCache"/>
        </list>
    </property>
    <property name="fallbackToNoOpCache" value="true"/>
</bean>
	
	
	
	If you are using the spring boot 1.3.0 or later versions, you have to follow these steps to enable the caching configurations:
•	Just add the spring-boot-starter-cache and caching implementation JAR file to the pom.xml file
•	Annotate with @EnableCaching in the @Configuration file
•	It is not required to add the caching manager beans in the @Configuration file. Spring boot will add the required beans based on the caching technology available in the classpath.
The following are list of caching technologies supported as of now. If you are using any one of these technologies, then spring boot can perform the auto-configuration for you:
	