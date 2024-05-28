package by.aston.webservice.ticketmicroservice.grpc;

import by.aston.webservice.ticketmicroservice.entity.Ticket;
import by.aston.webservice.ticketmicroservice.repository.TicketRepository;
import by.aston.webservice.ticketmicroservice.service.TicketServiceImpl;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.io.IOException;
import java.math.BigDecimal;

@GrpcService
@RequiredArgsConstructor
public class TicketServiceGrpc extends TicketServiceGrpcGrpc.TicketServiceGrpcImplBase {

    private final TicketRepository ticketRepository;


    @Override
    public void getTicketInfo(TicketServiceGrpcOuterClass.TicketRequest request, StreamObserver<TicketServiceGrpcOuterClass.TicketResponse> responseObserver) {

//        super.getTicketInfo(request, responseObserver);
//        System.out.println(request);
//
//        BigDecimal price = new BigDecimal(request.getPrice());
//
//        Ticket ticket = Ticket.builder().ticketNumber(request.getTicketNumber())
//                .passengerName(request.getPassengerName())
//                .seatNumber(request.getSeatNumber())
//                .price(price).build();
//
//        ticketRepository.save(ticket);
//

        String ticketNumber = request.getTicketNumber();
        String passengerName = request.getPassengerName();
        String seatNumber = request.getSeatNumber();
        String price = request.getPrice();

        TicketServiceGrpcOuterClass.TicketResponse response = TicketServiceGrpcOuterClass.TicketResponse.newBuilder()
                .setMessage("Ticket info received: " + ticketNumber + ", " + passengerName + ", " + seatNumber + ", " + price)
                .build();

        System.out.println(response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
