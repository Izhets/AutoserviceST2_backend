package ru.cs.vsu.ast2_backend.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.cs.vsu.ast2_backend.model.entity.car.CarModelEntity;
import ru.cs.vsu.ast2_backend.model.entity.car.CarTypeEntity;
import ru.cs.vsu.ast2_backend.model.entity.car.PartOnCarEntity;
import ru.cs.vsu.ast2_backend.model.entity.part.PartEntity;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "application")
public class ApplicationEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private PartEntity part;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "part_on_car_id")
//    private PartOnCarEntity partOnCar;
}