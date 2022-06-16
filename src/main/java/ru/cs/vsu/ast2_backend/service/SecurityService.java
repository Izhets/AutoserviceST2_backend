package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.model.dto.TokenRequestDto;
import ru.cs.vsu.ast2_backend.model.dto.TokenResponseDto;
import ru.cs.vsu.ast2_backend.service.security.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    private final JwtService jwtService;

    public TokenResponseDto getToken(TokenRequestDto tokenRequest) {
        var userFromTokenRequest = userService.getUserFromTokenRequest(tokenRequest);

        var tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(jwtService.getToken(userFromTokenRequest));

        return tokenResponseDto;

    }


}
