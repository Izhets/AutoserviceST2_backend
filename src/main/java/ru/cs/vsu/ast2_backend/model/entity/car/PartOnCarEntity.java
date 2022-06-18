package ru.cs.vsu.ast2_backend.model.entity.car;

import lombok.Getter;
import lombok.Setter;
import ru.cs.vsu.ast2_backend.model.entity.part.PartEntity;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "part_on_car")
public class PartOnCarEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private PartEntity part;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private CarModelEntity model;
}
