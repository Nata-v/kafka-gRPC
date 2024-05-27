package by.aston.webservice.emailnotification.handler;

import by.aston.webservice.emailnotification.dto.TicketDto;
import by.aston.webservice.emailnotification.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@KafkaListener(topics = "ticket-created-events-topic", groupId = "ticket-created-events")
@RequiredArgsConstructor
public class TicketCreatedEventHandler {

    private final TicketService ticketService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(TicketDto ticketDto) throws ExecutionException, InterruptedException {
        LOGGER.info("Received event: {}", ticketDto.toString());
        String newTicketInfo = ticketService.createTicket(ticketDto);
        LOGGER.info("Ticket has been created: {}", newTicketInfo);
    }


}
