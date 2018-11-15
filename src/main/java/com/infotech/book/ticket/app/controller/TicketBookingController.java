package com.infotech.book.ticket.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infotech.book.ticket.app.entities.Ticket;
import com.infotech.book.ticket.app.service.ProductBookingService;

@RestController
@RequestMapping(value="/api/tickets")
public class TicketBookingController {

	@Autowired
	private ProductBookingService ticketBookingService;
	
	@GetMapping(value="/{ticketId}")
	public Ticket getTicketById(@PathVariable("ticketId")Integer ticketId){
		return ticketBookingService.getTicketById(ticketId);
	}
	
	@DeleteMapping(value="/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId")Integer ticketId){
		ticketBookingService.deleteTicket(ticketId);
	}
	
	@PutMapping(value="/{ticketId}/{newEmail:.+}")
	public Ticket updateTicket(@PathVariable("ticketId")Integer ticketId,@PathVariable("newEmail")String newEmail){
		return ticketBookingService.updateTicket(ticketId,newEmail);
	}
	
	
	@GetMapping
	public List<Ticket> getTicketByBookingDate(@RequestParam("bookingDate")Date bookingDate){
		return ticketBookingService.getTicketByDate(bookingDate);
	}
	
}
