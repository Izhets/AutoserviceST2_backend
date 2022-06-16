package ru.cs.vsu.ast2_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.vsu.ast2_backend.model.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmailOrPhone(String email, String phone);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhone(String phone);

}
