package by.aston.webservice.emailnotification.controller;

import by.aston.webservice.emailnotification.dto.TicketDto;
import by.aston.webservice.emailnotification.grpc.TicketServiceGrpc;
import by.aston.webservice.emailnotification.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {


    private final TicketService ticketService;
    private final TicketServiceGrpc ticketServiceGrpc;

    @GetMapping("/info")
    public String getTicketInfo(
            @RequestParam String ticketNumber,
            @RequestParam String passengerName,
            @RequestParam String seatNumber,
            @RequestParam BigDecimal price) {

        return ticketServiceGrpc.getTicketInfo(ticketNumber, passengerName, seatNumber, price);
    }
    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody TicketDto ticketDto) throws ExecutionException, InterruptedException {
        String newTicket = ticketService.createTicket(ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("New ticket %s created successfully", newTicket));
    }
}
