package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RentalPoints {

    private String location;

    private RentalPointsStatus rentalPointsStatus;

    private Long id;

}
