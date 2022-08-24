package entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class Model {
    private Long id;

    private String brand;

    private String model;

    private int year;

    private double maxLoad;

    private double range;

    private double power;
}
