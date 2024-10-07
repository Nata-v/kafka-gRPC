package by.aston.webservice.ticketmicroservice.service;

import by.aston.webservice.ticketmicroservice.dto.TicketDto;

import java.util.concurrent.ExecutionException;

public interface TicketService {
    String createTicket(TicketDto ticketDto) throws ExecutionException, InterruptedException;
}
