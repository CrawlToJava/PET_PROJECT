package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Scooters {
    private Long id;

    private Double price;

    private Models model;

    private RentalPoints rentalPoint;

    private ScootersStatus scootersStatus;

    private Users users;

}
