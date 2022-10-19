package entity;

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
    @SequenceGenerator(name = "scooter_seq", sequenceName = " common_sequence ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scooter_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "rental_point_id", referencedColumnName = "id")
    private RentalPoint rentalPoint;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @Enumerated(EnumType.STRING)
    @Column(name = "scooter_status")
    private ScooterStatus scooterStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
