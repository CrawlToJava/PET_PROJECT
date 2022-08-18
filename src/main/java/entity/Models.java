package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Models {

    private String brand;

    private String model;

    private Integer year;

    private Double maxLoad;

    private Double range;

    private Integer power;

    private Long id;

}
