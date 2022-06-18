package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.vsu.ast2_backend.model.entity.part.PartCategoryEntity;

import java.util.UUID;

public interface PartCategoryRepository extends JpaRepository<PartCategoryEntity, UUID> {

    boolean existsByName(String name);
}
