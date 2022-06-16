package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.mapper.NewsMapper;
import ru.cs.vsu.ast2_backend.model.dto.NewsDto;
import ru.cs.vsu.ast2_backend.model.entity.NewsEntity;
import ru.cs.vsu.ast2_backend.repository.NewsRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public List<NewsDto> getNews() {
       var newsEntity= newsRepository.findAll();

        return newsMapper.toDto((List<NewsEntity>) newsEntity);
    }
}
