package com.infotech.book.ticket.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infotech.book.ticket.app.entities.Ticket;

public interface TicketBookingDao extends JpaRepository<Ticket, Integer>{

	public List<Ticket> findByBookingDate(Date bookingDate);
}
