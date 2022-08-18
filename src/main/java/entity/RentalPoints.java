package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RentalPoints {
    private Long id;

    private String location;

    private RentalPointsStatus rentalPointsStatus;


}
