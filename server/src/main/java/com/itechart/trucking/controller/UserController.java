package com.itechart.trucking.controller;

import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("isFullyAuthenticated()")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/count")
    public int getUserCount() {
        return service.getUserCount();
    }

    @RequestMapping("/get_users")
    public List<User> getUsersByPage(@RequestParam(value="page", defaultValue="1") String page){
        int pageNumber = Integer.parseInt(page);
        return service.getUsers(pageNumber);
    }

    @RequestMapping("/get_users")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }
}
