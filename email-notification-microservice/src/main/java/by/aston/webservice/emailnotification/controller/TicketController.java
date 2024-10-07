package by.aston.webservice.emailnotification.controller;

import by.aston.webservice.emailnotification.dto.TicketDto;
import by.aston.webservice.emailnotification.grpc.TicketServiceGrpc;
import by.aston.webservice.emailnotification.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {


    private final TicketService ticketService;
    private final TicketServiceGrpc ticketServiceGrpc;

    //http://localhost:9096/ticket/info?ticketNumber=ABC123&passengerName=Nata Volkova&seatNumber=12A&price=150.75
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
