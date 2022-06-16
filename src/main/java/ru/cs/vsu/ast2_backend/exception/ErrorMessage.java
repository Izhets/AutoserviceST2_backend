package ru.cs.vsu.ast2_backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private Integer statusCode;
    private String message;
}
