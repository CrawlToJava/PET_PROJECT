package application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scooters")
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scooter_generator")
    @SequenceGenerator(name = "scooter_generator", sequenceName = "scooters_seq", allocationSize = 1)
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rental_point_id", referencedColumnName = "id")
    private RentalPoint rentalPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @Enumerated(EnumType.STRING)
    @Column(name = "scooter_status")
    private ScooterStatus scooterStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Scooter(BigDecimal price, RentalPoint rentalPoint, Model model, ScooterStatus scooterStatus, User user) {
        this.price = price;
        this.rentalPoint = rentalPoint;
        this.model = model;
        this.scooterStatus = scooterStatus;
        this.user = user;
    }
}
