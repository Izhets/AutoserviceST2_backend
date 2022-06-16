package ru.cs.vsu.ast2_backend.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserCreateOrUpdateRequestDto {

    private String name;
    private String surname;

    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$")
    private String phone;

    @Email
    private String email;

    private String password;
    private String confirmPassword;

}