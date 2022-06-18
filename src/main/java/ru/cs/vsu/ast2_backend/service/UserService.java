package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.exception.BadRequestException;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.UserMapper;
import ru.cs.vsu.ast2_backend.model.dto.*;
import ru.cs.vsu.ast2_backend.model.entity.UserEntity;
import ru.cs.vsu.ast2_backend.repository.UserRepository;
import ru.cs.vsu.ast2_backend.util.SecurityUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public UserDto getUser(UUID id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found")));
    }

    public List<UserDto> getAllUsers() {
        var all = userRepository.findAll();
        return all.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDto) {
        var entity = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(entity);
    }

    public UserDto updateUser(UUID id, UserCreateOrUpdateRequestDto dto) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        var userEntity = userMapper.updateDto(user, dto);
        var saved = userRepository.save(userEntity);

        return userMapper.toDto(saved);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public UserDto withdrawMoney(Integer sum) {
        var userEntity = getProfileEntity();
        if (userEntity.getMoney() < sum) {
            throw new BadRequestException("Insufficient funds");
        }

        userEntity.setMoney(userEntity.getMoney() - sum);

        userRepository.save(userEntity);

        return userMapper.toDto(userEntity);
    }

    public String replenishBalance(ReplenishBalanceRequestDto requestDto) {
        var isSupportedPaymentType = Arrays
                .stream(PaymentType.values())
                .anyMatch(paymentType -> paymentType.name().equals(requestDto.getPaymentType()));

        if (!isSupportedPaymentType) {
            log.error("Unsupported payment type: {}", requestDto.getPaymentType());
            throw new BadRequestException("Unsupported payment type: %s", requestDto.getPaymentType());
        }

        var profileEntity = getProfileEntity();

        if (profileEntity.getMoney() == null || profileEntity.getMoney() <= 0) {
            profileEntity.setMoney(requestDto.getValue());
        } else {
            profileEntity.setMoney(profileEntity.getMoney() + requestDto.getValue());
        }

        userRepository.save(profileEntity);

        return "Payment was successful";
    }

    public UserDto updateProfile(UserCreateOrUpdateRequestDto requestDto) {
        var currentProfile = userMapper.toEntity(getProfile());

        if (!StringUtils.equals(requestDto.getPassword(), requestDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        var updatedEntity = userMapper.updateDto(currentProfile, requestDto);
        updatedEntity.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        return userMapper.toDto(userRepository.save(updatedEntity));
    }

    public UserDto getProfile() {
        return userMapper.toDto(getProfileEntity());
    }

    public UserEntity getProfileEntity() {
        var userId = SecurityUtil.getUserId();

        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found by id: {}", userId);
                    return new NotFoundException("User not found by id: %s", userId);
                });
    }

    public UserDto registerUser(UserCreateOrUpdateRequestDto requestDto) {
        if (userRepository.existsByEmailOrPhone(requestDto.getEmail(), requestDto.getPhone())) {
            throw new BadRequestException("User already exists");
        }

        if (!StringUtils.equals(requestDto.getPassword(), requestDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        var userEntity = userMapper.toEntity(requestDto);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(roleService.getUserRole());

        var saveResult = userRepository.save(userEntity);

        return userMapper.toDto(saveResult);
    }

    private UserEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            log.error("User not found by email: {}", email);
            return new NotFoundException("User not found by email: %s", email);
        });
    }


    private UserEntity getUserEntityByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> {
            log.error("User not found by phone: {}", phone);
            return new NotFoundException("User not found by phone: %s", phone);
        });
    }

    public boolean isPasswordsMatches(UserEntity userEntity, String password) {
        return passwordEncoder.matches(password, userEntity.getPassword());
    }

    public UserDto getUserFromTokenRequest(TokenRequestDto tokenRequest) {
        UserEntity userEntity = null;

        if (StringUtils.isNotBlank(tokenRequest.getEmail())) {
            userEntity = getUserEntityByEmail(tokenRequest.getEmail());
        }

        if (StringUtils.isNotBlank(tokenRequest.getPhoneNumber())) {
            userEntity = getUserEntityByPhone(tokenRequest.getPhoneNumber());
        }

        if (userEntity == null || !isPasswordsMatches(userEntity, tokenRequest.getPassword())) {
            throw new BadRequestException("Invalid user credentials");
        }

        return userMapper.toDto(userEntity);

    }
}
