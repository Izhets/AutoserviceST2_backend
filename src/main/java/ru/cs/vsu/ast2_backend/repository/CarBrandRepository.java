package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.vsu.ast2_backend.model.entity.car.CarBrandEntity;

import java.util.UUID;

public interface CarBrandRepository extends JpaRepository<CarBrandEntity, UUID> {

    boolean existsByName(String name);
}
