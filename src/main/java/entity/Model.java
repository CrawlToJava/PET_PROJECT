package entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(generator = "model_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "model_generator", sequenceName = "models_seq", allocationSize = 1)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "max")
    private double maxLoad;

    @Column(name = "range")
    private double range;

    @Column(name = "power")
    private double power;

    public Model(String brand, String model, int year, double maxLoad, double range, double power) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.maxLoad = maxLoad;
        this.range = range;
        this.power = power;
    }
}
