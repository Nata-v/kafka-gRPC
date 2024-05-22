package by.aston.webservice.ticketmicroservice.service;

import by.aston.webservice.ticketmicroservice.dto.TicketDto;
import by.aston.webservice.ticketmicroservice.entity.Ticket;
import by.aston.webservice.ticketmicroservice.mapper.TicketMapper;
import by.aston.webservice.ticketmicroservice.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private KafkaTemplate<String, TicketDto> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper, KafkaTemplate<String, TicketDto> kafkaTemplate) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createTicket(TicketDto ticketDto) throws ExecutionException, InterruptedException {

        Ticket ticket = ticketMapper.toTicket(ticketDto);
        ticketRepository.save(ticket);

        SendResult<String, TicketDto> result = kafkaTemplate
                .send("ticket-created-events-topic", ticketDto).get();

        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());

        LOGGER.info("Return: {}", ticket);
        return String.valueOf(ticket);
    }

}
