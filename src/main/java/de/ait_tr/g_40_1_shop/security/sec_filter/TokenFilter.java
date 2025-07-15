package de.ait_tr.g_40_1_shop.security.sec_filter;

import de.ait_tr.g_40_1_shop.security.AuthInfo;
import de.ait_tr.g_40_1_shop.security.sec_service.TokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {


    private TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
String token  = getTokenFromRequest((HttpServletRequest) servletRequest);
if (token!=null&& tokenService.validateAccesToken(token)){
    Claims claims = tokenService.getAccessClaims(token);
    AuthInfo authInfo = tokenService.mapClaimsToAuthInfo(claims);
    authInfo.setAuthenticated(true);
    SecurityContextHolder.getContext().setAuthentication(authInfo);
}

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
// Bearer erwerwerwecxvbcgbxcvx343kjh34h5h67hhh2  к токену добавляется преставка Bearer
        // Задача его вырезать из строки
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }return null;
    }
}
