package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Scooter {
    private Long id;

    private Double price;

    private Model model;

    private RentalPoint rentalPoint;

    private ScooterStatus scooterStatus;

    private User user;

}
