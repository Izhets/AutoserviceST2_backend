package ru.cs.vsu.ast2_backend.model.entity.car;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "car_type")
public class CarTypeEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<CarEntity> cars;
}