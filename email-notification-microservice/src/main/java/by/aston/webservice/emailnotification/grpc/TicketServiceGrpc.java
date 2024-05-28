package by.aston.webservice.emailnotification.grpc;

import by.aston.webservice.ticketmicroservice.grpc.TicketServiceGrpcGrpc;
import by.aston.webservice.ticketmicroservice.grpc.TicketServiceGrpcOuterClass;
import by.aston.webservice.ticketmicroservice.grpc.TicketServiceGrpcOuterClass.TicketRequest;
import by.aston.webservice.ticketmicroservice.grpc.TicketServiceGrpcOuterClass.TicketResponse;
import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.math.BigDecimal;


@Service
public class TicketServiceGrpc {

   @GrpcClient("ticketService")
   private TicketServiceGrpcGrpc.TicketServiceGrpcBlockingStub stub;


   public String getTicketInfo(String ticketNumber, String passengerName, String seatNumber, BigDecimal price) {

      System.out.println(passengerName);
      System.out.println(seatNumber);
      TicketRequest request = TicketRequest.newBuilder()
              .setTicketNumber(ticketNumber)
              .setPassengerName(passengerName)
              .setSeatNumber(seatNumber)
              .setPrice(price.toString())
              .build();


      TicketResponse response = stub.getTicketInfo(request);
//      System.out.println(response.getMessage());
      return response.getMessage();
   }



}
