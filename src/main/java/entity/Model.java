package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Model {
    private Long id;

    private String brand;

    private String model;

    private int year;

    private Double maxLoad;

    private Double range;

    private Integer power;


}
