package entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class Scooter {
    private Long id;

    private BigDecimal price;

    private Model model;

    private RentalPoint rentalPoint;

    private ScooterStatus scooterStatus;

    private User user;

}
