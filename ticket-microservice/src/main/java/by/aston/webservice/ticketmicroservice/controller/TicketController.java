package by.aston.webservice.ticketmicroservice.controller;

import by.aston.webservice.ticketmicroservice.dto.TicketDto;
import by.aston.webservice.ticketmicroservice.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/ticket")
public class TicketController {

        private final TicketService ticketService;
        private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
        public ResponseEntity<Object> createTicket(@RequestBody TicketDto ticketDto){
            String ticketId = null;
            try {
                ticketId = ticketService.createTicket(ticketDto);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorMessage(new Date(),e.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketId);
        }
}
