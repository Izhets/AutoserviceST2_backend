package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.RoleMapper;
import ru.cs.vsu.ast2_backend.model.dto.RoleDto;
import ru.cs.vsu.ast2_backend.model.entity.RoleEntity;
import ru.cs.vsu.ast2_backend.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    public static final String USER_ROLE = "pk-user";
    public static final String ADMIN_ROLE = "pk-admin";

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleEntity getUserRole() {
        return getRoleEntityByName(USER_ROLE);
    }


    public RoleDto getRoleDtoByName(String name) {
        return roleMapper.toDto(getRoleEntityByName(name));
    }

    public RoleEntity getRoleEntityByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> {
            log.error("Role not found by name: {}", name);
            return new NotFoundException("Role not found by name: %s", name);
        });
    }

    public List<RoleDto> getRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }
}
