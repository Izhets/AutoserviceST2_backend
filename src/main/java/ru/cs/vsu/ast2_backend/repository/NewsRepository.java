package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cs.vsu.ast2_backend.model.entity.NewsEntity;

import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, UUID> {
}
