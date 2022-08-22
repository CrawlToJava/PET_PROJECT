package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Order {
    private Long id;

    private LocalDateTime orderedAt;

    private LocalDateTime finishedAt;

    private double totalPrice;

    private OrderStatus orderStatus;

    private User user;

    private Scooter scooter;

    private RentalPoint rentalPoint;

}
