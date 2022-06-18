package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.ApplicationMapper;
import ru.cs.vsu.ast2_backend.mapper.CarMapper;
import ru.cs.vsu.ast2_backend.model.dto.ApplicationDto;
import ru.cs.vsu.ast2_backend.model.dto.CarDto;
import ru.cs.vsu.ast2_backend.repository.ApplicationRepository;
import ru.cs.vsu.ast2_backend.repository.CarRepository;
import ru.cs.vsu.ast2_backend.util.SecurityUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.cs.vsu.ast2_backend.util.SecurityUtil.getUserId;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public List<ApplicationDto> getApplicationsByUser() {
        return applicationRepository.findAllByOwnerId(getUserId())
                .stream()
                .map(applicationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ApplicationDto getUserApplicationById(UUID id) {
        var applicationEntity = applicationRepository.findByIdAndOwnerId(id, getUserId()).orElseThrow(() -> {
            log.error("Car not found by id {} and userId {}", id, getUserId());
            throw new NotFoundException("Car not found by id %s and userId %s", id, SecurityUtil.getUserId());
        });

        return applicationMapper.toDto(applicationEntity);
    }

    public ApplicationDto getCarById(UUID id) {
        var carEntity = applicationRepository.findById(id).orElseThrow(() -> {
            log.error("Car not found by id={} ", id);
            throw new NotFoundException("Car not found by id=%s ", id);
        });

        return applicationMapper.toDto(carEntity);
    }

    public ApplicationDto addApplication(ApplicationDto applicationDto) {
        applicationDto.setOwnerId(getUserId());

        var applicationEntity = applicationRepository.save(applicationMapper.toEntity(applicationDto));

        return applicationMapper.toDto(applicationEntity);
    }

    public ApplicationDto updateApplication(UUID id, ApplicationDto applicationDto) {
        var updatedDto = applicationMapper.updateDto(getUserApplicationById(id), applicationDto);

        var result = applicationRepository.save(applicationMapper.toEntity(updatedDto));

        return applicationMapper.toDto(result);
    }

    @Transactional
    public void deleteApplication(UUID id) {
        var userApplication = getUserApplicationById(id);
        applicationRepository.deleteById(userApplication.getId());
    }

    public List<UUID> getUserApplicationsIds() {
        return applicationRepository.findCarIdsByUserId(getUserId());
    }

    public boolean isUserApplication(UUID applicationId, UUID userId) {
        return applicationRepository.existsByIdAndOwnerId(applicationId, userId);
    }

}
