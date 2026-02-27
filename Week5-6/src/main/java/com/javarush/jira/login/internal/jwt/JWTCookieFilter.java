package com.javarush.jira.login.internal.jwt;

import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.internal.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JWTCookieFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = null;

        if (request.getCookies() != null) {
            jwt = Arrays.stream(request.getCookies())
                    .filter(c -> "JWT".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }

        if (jwt != null && jwtUtil.validateToken(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String email = jwtUtil.getEmailFromToken(jwt);
            AuthUser authUser = new AuthUser(userRepository.getExistedByEmail(email));

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    authUser, null, authUser.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}