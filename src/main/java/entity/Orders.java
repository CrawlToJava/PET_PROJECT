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
public class Orders {

    private LocalDateTime orderedAt;

    private LocalDateTime finishedAt;

    private Double totalPrice;

    private OrdersStatus ordersStatus;

    private User user;

    private Scooters scooters;

    private RentalPoints rentalPoints;

    private Long id;
}
