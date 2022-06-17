package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.vsu.ast2_backend.model.entity.car.CarTypeEntity;

import java.util.Optional;
import java.util.UUID;

public interface CarTypeRepository extends JpaRepository<CarTypeEntity, UUID> {

    boolean existsByType(String type);

    Optional<CarTypeEntity> findByType(String type);
}
