package ru.cs.vsu.ast2_backend.model.car;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "car_model")
public class CarModelEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private CarBrandEntity brand;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<CarEntity> cars;
}
