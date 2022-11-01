package application.entity;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_point_generator")
    @SequenceGenerator(name = "rental_point_generator", sequenceName = "rental_points_seq", allocationSize = 1)
    private Long id;

    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "rental_point_status")
    private RentalPointStatus rentalPointsStatus;

    public RentalPoint(String location, RentalPointStatus rentalPointsStatus) {
        this.location = location;
        this.rentalPointsStatus = rentalPointsStatus;
    }
}
