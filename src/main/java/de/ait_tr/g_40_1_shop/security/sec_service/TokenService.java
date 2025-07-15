package de.ait_tr.g_40_1_shop.security.sec_service;

import de.ait_tr.g_40_1_shop.domain.entity.Role;
import de.ait_tr.g_40_1_shop.domain.entity.User;
import de.ait_tr.g_40_1_shop.repository.RoleRepository;
import de.ait_tr.g_40_1_shop.security.AuthInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TokenService {
    private SecretKey accessKey;
    private SecretKey refreshKey;
    private RoleRepository roleRepository;

    public TokenService(
            @Value("${key.access}") String accessSecretKey,
            @Value("${key.refresh}") String refreshSecretKey,
            RoleRepository roleRepository) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecretKey));
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecretKey));
        this.roleRepository = roleRepository;
    }


    // Первая процедура
    //Создание даты истечения AccessToken
    public String generateAccessToken(User user) {
        LocalDateTime currentDate = LocalDateTime.now();
        Instant expiration = currentDate.plusDays(7).atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expiration);

//создание acces token
        String compact = Jwts.builder()
                .subject(user.getUsername())
                .expiration(expirationDate)
                .signWith(accessKey)
                .claim("roles", user.getAuthorities())
                .claim("name", user.getUsername()).compact();

        return compact;
    }

    //Создание даты истечения refreshToken
    public String generateRefreshToken(User user) {
        LocalDateTime currentDate = LocalDateTime.now();
        Instant expiration = currentDate.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expiration);
//создание refresh token
        String compact = Jwts.builder()
                .subject(user.getUsername())
                .expiration(expirationDate)
                .signWith(refreshKey)
                .compact();

        return compact;
    }

// Вторая процедура

    //процесс Validation Tokens Общий метод для валидации всех Token
    private boolean validateToken(String token, SecretKey secretKey) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean validateAccesToken(String accessToken) {
        return validateToken(accessToken, accessKey);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, refreshKey);
    }

    // Третья процедура
    // Извлечение из Token Всю Информацию о Пользователе
    // Общий метод  Извлечение из Token всей информации
    private Claims getClaims(String token, SecretKey key) {
        Claims payload = Jwts.parser().verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return payload;
    }

    public Claims getAccessClaims(String accessToken) {
        return getClaims(accessToken, accessKey);
    }

    public Claims getRefreshClaims(String refreshToken) {
        return getClaims(refreshToken, refreshKey);
    }

    // Четвертая процедура
    // конвертация объекта CLAIMS в объект AuthInfo, потому что SpringSecurity понимает объект AutoInfo

    public AuthInfo mapClaimsToAuthInfo(Claims claims) {
        String userName = claims.getSubject();
        List<LinkedHashMap<String, String>> listRoles =
                (List<LinkedHashMap<String, String>>) claims.get("roles");
        Set<Role> roleSet = new HashSet<>();
        for (LinkedHashMap<String, String> rolesEntry : listRoles) {
            String roleTitle = rolesEntry.get("authority");
            Role role = roleRepository.findRoleByTitle(roleTitle).orElse(null);
            if (role != null) {
                roleSet.add(role);
            }
        }
        return new AuthInfo(userName, roleSet);
    }
}
