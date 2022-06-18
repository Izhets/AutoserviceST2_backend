package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.vsu.ast2_backend.model.entity.part.PartEntity;

import java.util.UUID;

public interface PartRepository extends JpaRepository<PartEntity, UUID> {


}
