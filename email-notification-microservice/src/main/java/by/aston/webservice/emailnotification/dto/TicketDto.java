package by.aston.webservice.emailnotification.dto;

import java.math.BigDecimal;

public record TicketDto(Long id,
                        String ticketNumber,
                        String passengerName,
                        String seatNumber,
                        BigDecimal price) {
}
