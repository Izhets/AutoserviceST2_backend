package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.vsu.ast2_backend.model.entity.car.CarModelEntity;

import java.util.List;
import java.util.UUID;

public interface CarModelRepository extends JpaRepository<CarModelEntity, UUID> {

    List<CarModelEntity> findAllByBrandId(UUID brandId);

}
