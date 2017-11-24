package com.itechart.trucking.auth;


import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Component
public final class TokenHandlerImpl implements TokenHandler {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TokenHandlerImpl.class);

    private final String secret;

    private final UserDao userDao;

    @Autowired
    public TokenHandlerImpl(@Value("${app.jwt.secret}") String secret,
                            UserDao userDao) {
        this.secret = secret;
        this.userDao = userDao;
    }

    @Override
    public Optional<UserDetails> parseUserFromToken(String token) {
        final String subject = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<User> user = userDao.getUserById(Integer.valueOf(subject));

        return Optional.ofNullable(user.orElse(null));
    }

    @Override
    public String createTokenForUser(User user) {
        final ZonedDateTime afterOneWeek = ZonedDateTime.now().plusWeeks(1);

        return Jwts.builder()
                .setSubject(user.getId().toString()) // expiration + session
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(Date.from(afterOneWeek.toInstant()))
                .compact();
    }
}

