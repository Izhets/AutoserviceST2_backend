package ru.cs.vsu.ast2_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String content;
    private String imgUrl;

}
