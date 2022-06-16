package ru.cs.vsu.ast2_backend.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.cs.vsu.ast2_backend.service.security.Roles;

import java.util.UUID;

public final class SecurityUtil {

    private static final String PREFIX = "ROLE_";

    private SecurityUtil() {
    }

    public static UUID getUserId() {
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            var principalObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return UUID.fromString(principalObj.toString());
        } else throw new SecurityException("Unauthorized user");
    }

    public static boolean hasRole(String role) {
        var normalizedRole = PREFIX + role;

        if (SecurityContextHolder.getContext().getAuthentication() != null) {

            return SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getAuthorities()
                    .stream()
                    .map(SimpleGrantedAuthority.class::cast)
                    .anyMatch(simpleGrantedAuthority -> normalizedRole.equals(simpleGrantedAuthority.getAuthority()));
        }

        return false;
    }

    public static boolean isAnonymous() {
        return hasRole(Roles.ANONYMOUS);
    }
}
