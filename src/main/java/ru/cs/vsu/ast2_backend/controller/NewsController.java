package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cs.vsu.ast2_backend.model.dto.NewsDto;
import ru.cs.vsu.ast2_backend.service.NewsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
@Tag(name = "Новости")
public class NewsController {

    private final NewsService newsService;

    @GetMapping()
    public ResponseEntity<List<NewsDto>> getNews(){
        return ResponseEntity.ok(newsService.getNews());
    }

}
