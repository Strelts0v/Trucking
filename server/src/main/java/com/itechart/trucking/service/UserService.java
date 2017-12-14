package com.itechart.trucking.service;

import com.itechart.trucking.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    /**
     * Returns all instances of the {@link User} by the given page number and size.
     *
     * @param page     - offset of instances
     * @param pageSize - count of instances in page
     * @return list of users objects per page
     */
    List<User> getUsers(int page, int pageSize);

    /**
     * gets user by id from storage
     *
     * @param id - unique id of existed user
     * @return Optional user object
     */
    Optional<User> getUserById(Integer id);

    /**
     * get user by email from storage
     *
     * @param email - email address of existed user
     * @return Optional user object
     */
    Optional<User> getUserByEmail(String email);

    /**
     * returns all users from the storage
     */
    List<User> getAllUsers();

    /**
     * gets count of users in storage
     * @return count of user objects in storage
     */
    int getUserCount();

    /**
     * adds new user to the storage
     *
     * @param user - new client object
     * @return added user object with unique id
     */
    User addUser(User user);

    /**
     * updates existed user in storage
     *
     * @param user - new user with id, if user doesn't contain
     *             id, user object will be saved in storage
     */
    void updateUser(User user);

    /**
     * deletes existed user in storage
     *
     * @param user - existed client with id in storage
     */
    void deleteUser(User user);

    /**
     * gets users by specified role
     */
    List<User> getUsersByRole(User.Role role);
}
