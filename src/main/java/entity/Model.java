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
    @SequenceGenerator(name = "model_seq", sequenceName = " common_sequence ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_seq")
    @Column(name = "id", updatable = false)
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
}
