package com.itechart.trucking.service;

import com.itechart.trucking.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    int getUserCount();

    List<User> getUsers(int page);
}
