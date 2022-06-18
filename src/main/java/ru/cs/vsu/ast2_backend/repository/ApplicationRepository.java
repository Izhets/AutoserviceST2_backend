package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cs.vsu.ast2_backend.model.entity.ApplicationEntity;
import ru.cs.vsu.ast2_backend.model.entity.car.CarEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, UUID> {

    List<ApplicationEntity> findAllByOwnerId(UUID ownerId);

    Optional<ApplicationEntity> findByIdAndOwnerId(UUID id, UUID ownerId);

    @Query("select application.id from ApplicationEntity application " +
            "where application.owner.id = :userId")
    List<UUID> findCarIdsByUserId(UUID userId);

    boolean existsByIdAndOwnerId(UUID applicationId, UUID ownerId);
}
