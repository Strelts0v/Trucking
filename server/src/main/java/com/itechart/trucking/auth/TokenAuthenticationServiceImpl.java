package com.itechart.trucking.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.itechart.trucking.auth.SecurityConstants.*;

@Service
class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    private final TokenHandler tokenHandler;

    @Autowired
    TokenAuthenticationServiceImpl(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String authHeader = request.getHeader(HEADER_STRING);
        if (authHeader == null) return null;
        if (!authHeader.startsWith(TOKEN_PREFIX)) return null;

        final String jwt = authHeader.substring(TOKEN_PREFIX.length());
        if (jwt.isEmpty()) return null;

        return tokenHandler
                .parseUserFromToken(jwt)
                .map(UserAuthentication::new)
                .orElse(null);
    }
}

