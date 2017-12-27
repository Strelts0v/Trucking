package com.itechart.trucking.service;

import com.itechart.trucking.domain.User;

import java.util.Optional;

public interface SecurityContextService {

    Optional<User> currentUser();

}
