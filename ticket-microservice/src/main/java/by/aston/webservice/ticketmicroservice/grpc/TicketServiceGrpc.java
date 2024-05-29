package by.aston.webservice.ticketmicroservice.grpc;

import by.aston.webservice.ticketmicroservice.repository.TicketRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
@RequiredArgsConstructor
public class TicketServiceGrpc extends TicketServiceGrpcGrpc.TicketServiceGrpcImplBase {

    private final TicketRepository ticketRepository;
    @Override
    public void getTicketInfo(TicketServiceGrpcOuterClass.TicketRequest request, StreamObserver<TicketServiceGrpcOuterClass.TicketResponse> responseObserver) {

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
