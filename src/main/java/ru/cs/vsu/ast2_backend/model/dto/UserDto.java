package ru.cs.vsu.ast2_backend.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {

    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private RoleDto role;
    private Integer money;
}