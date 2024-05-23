package by.aston.webservice.ticketmicroservice.grpc;

import by.aston.webservice.ticketmicroservice.entity.Ticket;
import by.aston.webservice.ticketmicroservice.repository.TicketRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.math.BigDecimal;

@GrpcService
@RequiredArgsConstructor
public class TicketServiceGrpc extends TicketServiceGrpcGrpc.TicketServiceGrpcImplBase {

    private final TicketRepository ticketRepository;

    @Override
    public void getTicketInfo(TicketServiceGrpcOuterClass.TicketRequest request, StreamObserver<TicketServiceGrpcOuterClass.TicketResponse> responseObserver) {

        super.getTicketInfo(request, responseObserver);
        System.out.println(request);

        BigDecimal price = new BigDecimal(request.getPrice());

        Ticket ticket = Ticket.builder().ticketNumber(request.getTicketNumber())
                .passengerName(request.getPassengerName())
                .seatNumber(request.getSeatNumber())
                .price(price).build();

        ticketRepository.save(ticket);

        TicketServiceGrpcOuterClass.TicketResponse response = TicketServiceGrpcOuterClass.TicketResponse.newBuilder()
                .setMessage("Ticket created successful! " + " TicketNumber: " + request.getTicketNumber()
                        + " PassengerName: " + request.getPassengerName() + " SeatNumber: " + request.getSeatNumber()).build();

        System.out.println(response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
