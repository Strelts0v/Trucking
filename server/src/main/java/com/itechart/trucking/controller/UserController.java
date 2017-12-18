package com.itechart.trucking.controller;

import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.itechart.trucking.util.Constants.NUMBER_REGEX;

@RestController
@RequestMapping("/api/users")
//@Secured({AUTHORIZED_ROLE_SYSADMIN, AUTHORIZED_ROLE_OWNER})
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping("/count")
    public ResponseEntity<Integer> getUserCount() {
        int userCount = service.getUserCount();
        return new ResponseEntity<>(userCount, HttpStatus.OK);
    }

    @GetMapping("/get_users/{page:" + NUMBER_REGEX + "}/{size:" + NUMBER_REGEX + "}")
    public ResponseEntity<List<User>> getUsersByPage(@PathVariable int page, @PathVariable int size){
        log.debug("REST request for users by page {0} size {1}", page, size);
        List<User> users = service.getUsers(page, size);
        log.debug("Returning users by page {0} size {1}: {2}", page, size, users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add_user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        log.debug("REST request for adding a new User: {}", user);
        service.addUser(user);
        log.debug("added user: {}", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update_user")
    public ResponseEntity<Void> updateUser(@RequestParam(value="user") User user){
        log.debug("REST request for updating user: {}", user);
        service.updateUser(user);
        log.info("updated user: " + user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete_user")
    public ResponseEntity<Void> deleteUser (@RequestParam(value="user") User user){
        log.debug("REST request for deleting user: {}", user);
        service.deleteUser(user);
        log.info("deleted user: " + user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_users_by_role")
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
