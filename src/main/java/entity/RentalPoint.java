package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rental_points")
public class RentalPoint {
    @Id
    @SequenceGenerator(name = "rental_point_seq", sequenceName = " common_sequence ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_point_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "rental_point_status")
    private RentalPointStatus rentalPointsStatus;
}
