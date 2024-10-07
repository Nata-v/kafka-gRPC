package by.aston.webservice.emailnotification.service;

import by.aston.webservice.emailnotification.dto.TicketDto;

import java.util.concurrent.ExecutionException;

public interface TicketService {
    String createTicket(TicketDto ticketDto) throws ExecutionException, InterruptedException;

}
