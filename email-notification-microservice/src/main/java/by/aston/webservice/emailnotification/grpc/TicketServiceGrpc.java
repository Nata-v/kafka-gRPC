package by.aston.webservice.emailnotification.grpc;

import by.aston.webservice.ticketmicroservice.grpc.TicketServiceGrpcGrpc;
import by.aston.webservice.ticketmicroservice.grpc.TicketServiceGrpcOuterClass;
import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.math.BigDecimal;


@Service
public class TicketServiceGrpc {

   @GrpcClient("ticketService")
   private TicketServiceGrpcGrpc.TicketServiceGrpcBlockingStub stub;


   public String getTicketInfo(String ticketNumber, String passengerName, String seatNumber, BigDecimal price) {
      System.out.println(ticketNumber);
      TicketServiceGrpcOuterClass.TicketRequest request = TicketServiceGrpcOuterClass.TicketRequest
              .newBuilder().setTicketNumber(ticketNumber).setPassengerName(passengerName)
              .setSeatNumber(seatNumber).setPrice(price.toString()).build();


      TicketServiceGrpcOuterClass.TicketResponse response = stub.getTicketInfo(request);
      System.out.println(response);
      return response.getMessage();
   }


}
