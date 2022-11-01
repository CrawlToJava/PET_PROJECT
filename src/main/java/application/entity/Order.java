package application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "orders_seq", allocationSize = 1)
    private Long id;

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scooter_id", referencedColumnName = "id")
    private Scooter scooter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rental_point_id", referencedColumnName = "id")
    private RentalPoint rentalPoint;

    public Order(LocalDateTime orderedAt, LocalDateTime finishedAt, BigDecimal totalPrice, OrderStatus orderStatus, User user, Scooter scooter, RentalPoint rentalPoint) {
        this.orderedAt = orderedAt;
        this.finishedAt = finishedAt;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.user = user;
        this.scooter = scooter;
        this.rentalPoint = rentalPoint;
    }
}
