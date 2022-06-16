package ru.cs.vsu.ast2_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    private String title;
    private String content;
    private String imgUrl;

}
