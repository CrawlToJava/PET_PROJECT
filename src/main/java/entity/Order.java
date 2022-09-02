package entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class Order {

    private Long id;

    private LocalDateTime orderedAt;

    private LocalDateTime finishedAt;

    private BigDecimal totalPrice;

    private OrderStatus orderStatus;

    private User user;

    private Scooter scooter;

    private RentalPoint rentalPoint;
}
