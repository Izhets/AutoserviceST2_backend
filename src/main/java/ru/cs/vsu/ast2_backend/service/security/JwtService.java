package ru.cs.vsu.ast2_backend.service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.model.dto.RoleDto;
import ru.cs.vsu.ast2_backend.model.dto.UserDto;
import ru.cs.vsu.ast2_backend.exception.SecurityException;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final ObjectMapper objectMapper;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.issuer}")
    private String issuer;

    @Value("${token.expiresIn}")
    private Integer expiresIn;

    private Date getExpireDate() {
        return Date.from(Instant.now().plusSeconds(expiresIn));
    }

    public String getToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject(userDto.getId().toString())
                .setIssuer(issuer)
                .setExpiration(getExpireDate())
                .claim("email", userDto.getEmail())
                .claim("role", userDto.getRole().getName())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public UserDto parseJwt(String token) {
        var tokenClaims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        if (!StringUtils.equals(tokenClaims.getIssuer(), issuer)) {
            throw new SecurityException("Unknown token issuer: {}", tokenClaims.getIssuer());
        }

        if (tokenClaims.getExpiration().before(Date.from(Instant.now()))) {
            throw new SecurityException("Token is not active");
        }

        var userDto = new UserDto();

        userDto.setId(objectMapper.convertValue(tokenClaims.getSubject(), UUID.class));
        userDto.setEmail(objectMapper.convertValue(tokenClaims.get("email"), String.class));

        var roleName = objectMapper.convertValue(tokenClaims.get("role"), String.class);
        var role = new RoleDto();

        role.setName(roleName);
        userDto.setRole(role);

        return userDto;
    }
}
