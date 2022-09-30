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

    private RentalPoint rentalPoint;

    private Model model;

    private ScooterStatus scooterStatus;

    private User user;
}
