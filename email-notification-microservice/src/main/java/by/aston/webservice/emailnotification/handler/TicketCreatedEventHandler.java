package by.aston.webservice.emailnotification.handler;

import by.aston.webservice.emailnotification.dto.TicketDto;
import by.aston.webservice.emailnotification.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@KafkaListener(topics = "ticket-created-events-topic", groupId = "ticket-created-events")

public class TicketCreatedEventHandler {
//    private final ProductService productService;
//
//    public ProductCreatedEventHandler(ProductService productService) {
//        this.productService = productService;
//    }

    private final TicketService ticketService;

    public TicketCreatedEventHandler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//    @KafkaHandler
//    public void handle(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
//        LOGGER.info("Received event: {}", createProductDto.getTitle());
//        String newProductInfo = productService.createProduct(createProductDto);
//        LOGGER.info("Product has been created: {}", newProductInfo);
//    }

    @KafkaHandler
    public void handle(TicketDto ticketDto) throws ExecutionException, InterruptedException {
        LOGGER.info("Received event: {}, {}", ticketDto.passengerName(), ticketDto.seatNumber());
        String newTicketInfo = ticketService.createTicket(ticketDto);
        LOGGER.info("Ticket has been created: {}", newTicketInfo);
    }


}
