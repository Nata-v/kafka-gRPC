package by.aston.webservice.ticketmicroservice.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record TicketDto (
                         String ticketNumber,
                         String passengerName,
                         String seatNumber,
                         BigDecimal price){


}
