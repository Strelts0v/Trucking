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
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getUsers(int page, int pageSize) {
        return userDao.getUsersByPage(page, pageSize);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public List<User> getUsersByRole(User.Role role) {
        return userDao.getUsersByRole(role);
    }
}