package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cs.vsu.ast2_backend.model.entity.car.CarEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {

    List<CarEntity> findAllByOwnerId(UUID ownerId);

    Optional<CarEntity> findByIdAndOwnerId(UUID id, UUID ownerId);

    @Query("select car.id from CarEntity car " +
            "where car.owner.id = :userId")
    List<UUID> findCarIdsByUserId(UUID userId);

    Optional<CarEntity> findByNumber(String number);

    boolean existsByIdAndOwnerId(UUID carId, UUID ownerId);

}
