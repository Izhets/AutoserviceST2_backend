package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.vsu.ast2_backend.model.entity.RoleEntity;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    Optional<RoleEntity> findByName(String name);
}
