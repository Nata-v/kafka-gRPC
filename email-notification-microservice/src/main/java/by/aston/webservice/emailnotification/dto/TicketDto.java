package by.aston.webservice.emailnotification.dto;

import java.math.BigDecimal;

public record TicketDto(
                        String ticketNumber,
                        String passengerName,
                        String seatNumber,
                        BigDecimal price) {
}
