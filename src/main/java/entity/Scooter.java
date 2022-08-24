package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Scooter {
    private Long id;

    private BigDecimal price;

    private Model model;

    private RentalPoint rentalPoint;

    private ScooterStatus scooterStatus;

    private User user;

}
