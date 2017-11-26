package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SecurityContextServiceImpl implements SecurityContextService {

    private final UserDao userDao;

    @Autowired
    public SecurityContextServiceImpl(UserDao userRepository) {
        this.userDao = userRepository;
    }

    @Override
    public Optional<User> currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userDao.getUserByEmail(authentication.getName());
    }
}
