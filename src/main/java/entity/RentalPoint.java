package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RentalPoint {
    private Long id;

    private String location;

    private RentalPointStatus rentalPointsStatus;


}
