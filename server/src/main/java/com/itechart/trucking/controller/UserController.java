package com.itechart.trucking.controller;

import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("isFullyAuthenticated()")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @RequestMapping("/count")
    public ResponseEntity<Integer> getUserCount() {
        int userCount = service.getUserCount();
        return new ResponseEntity<>(userCount, HttpStatus.OK);
    }

    @RequestMapping("/get_users")
    public ResponseEntity<List<User>> getUsersByPage(
            @RequestParam(value="page", defaultValue="1") String page,
            @RequestParam(value="pageSize", defaultValue="20") String pageSize){
        int pageNumber = Integer.parseInt(page);
        int userCount = Integer.parseInt(pageSize);
        List<User> users = service.getUsers(pageNumber, userCount);
        log.info("return users by page " + page + " pageSize " + pageSize + ": " + users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping("/add_user")
    public ResponseEntity<User> addUser(@RequestParam(value="user") User user){
        User userWithId = service.addUser(user);
        log.info("added user: " + userWithId);
        return new ResponseEntity<>(userWithId, HttpStatus.OK);
    }

    @RequestMapping("/update_user")
    public ResponseEntity<Void> updateUser(@RequestParam(value="user") User user){
        service.updateUser(user);
        log.info("updated user: " + user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/delete_user")
    public ResponseEntity<Void> deleteUser (@RequestParam(value="user") User user){
        service.deleteUser(user);
        log.info("deleted user: " + user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/get_users_by_role")
    public ResponseEntity<List<User>> getUsersByRole (
            @RequestParam(value="role", defaultValue = "driver") String role) {
        User.Role roleEnum = User.Role.DRIVER;
        try {
            roleEnum = User.Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
            log.error("Return users drivers");
        }
        List<User> users = service.getUsersByRole(roleEnum);
        log.info("return users by role " + role + ": " + users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
