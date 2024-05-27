package by.aston.webservice.emailnotification.service;

import by.aston.webservice.emailnotification.dto.TicketDto;
import by.aston.webservice.emailnotification.entity.Ticket;
import by.aston.webservice.emailnotification.mapper.TicketMapper;
import by.aston.webservice.emailnotification.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final KafkaTemplate<String, TicketDto> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());



    @Override
    public String createTicket(TicketDto ticketDto) throws ExecutionException, InterruptedException {

        Ticket ticket = ticketMapper.toTicket(ticketDto);
        ticketRepository.save(ticket);

        SendResult<String, TicketDto> result = kafkaTemplate
                .send("order-created-events-topic", ticketDto).get();

        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());

        LOGGER.info("Return: {}", ticket);
        return String.valueOf(ticket);
    }
}
