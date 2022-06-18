package ru.cs.vsu.ast2_backend.model.entity.part;

import lombok.Getter;
import lombok.Setter;
import ru.cs.vsu.ast2_backend.model.entity.car.PartOnCarEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "part")
public class PartEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private PartCategoryEntity category;

    @OneToMany(mappedBy = "part", fetch = FetchType.LAZY)
    private List<PartOnCarEntity> carPart;
}
