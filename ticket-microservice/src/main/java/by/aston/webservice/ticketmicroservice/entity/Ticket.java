package by.aston.webservice.ticketmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "price")
    private BigDecimal price;



    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", price=" + price +
                '}';
    }
}
