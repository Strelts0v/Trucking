package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.SecurityContextService;
import com.itechart.trucking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final SecurityContextService securityContextService;
    private final UserDao userDao;

    private static final int USERS_PER_PAGE = 20;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           SecurityContextService securityContextService) {
        this.userDao = userDao;
        this.securityContextService = securityContextService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userDao.getUserByEmail(username);
        final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        user.ifPresent(detailsChecker::check);
        return user.orElseThrow(() -> new UsernameNotFoundException("user not found."));
    }

    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }

    @Override
    public List<User> getUsers(int page) {
        int offset = (page - 1) * USERS_PER_PAGE;
        return userDao.getUsersByPage(offset, USERS_PER_PAGE);
    }
}