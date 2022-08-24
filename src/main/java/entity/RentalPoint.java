package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class RentalPoint {
    private Long id;

    private String location;

    private RentalPointStatus rentalPointsStatus;
}
