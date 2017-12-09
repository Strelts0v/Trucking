package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the JpaClientDao.
 *
 * @see JpaUserDao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class JpaUserDaoTest {

    @Autowired
    private UserDao dao;

    private User user;

    private static final String USER_NAME = "test_email@gmail.com";

    @Before
    public void setUp(){
        user = new User();
        user.setEmail(USER_NAME);
    }

    @Test
    public void getUserByIdShouldReturnNoUserTest() throws Exception {
        final int invalidUserId = 5;
        Optional<User> user = dao.getUserById(invalidUserId);

        final String errorMessage = "Expected empty optional user object";
        Assert.assertFalse(errorMessage, user.isPresent());
    }

    @Test
    public void getUserByIdShouldReturnCorrespondUser() throws Exception {
        User expectedUser = dao.addUser(user);
        final int addedUserId = expectedUser.getId();
        Optional<User> actualUser = dao.getUserById(addedUserId);

        final String errorMessage = "Expected and actual user are different";
        assertEquals(errorMessage, expectedUser, actualUser.orElse(new User()));
    }

    @Test
    public void getUserByEmailShouldReturnExactUserCount() throws Exception {
        dao.addUser(user);

        User user1 = new User();
        user1.setEmail("test@name");
        dao.addUser(user1);

        final String searchEmail = "test@email.com";
        User expectedUser = new User();
        expectedUser.setEmail(searchEmail);
        dao.addUser(expectedUser);

        Optional<User> actualUser = dao.getUserByEmail(searchEmail);

        final String errorMessage = "Expected and actual user size are different";
        assertEquals(errorMessage, expectedUser, actualUser.orElse(new User()));
    }

    @Test
    public void getUsersByExistedPageShouldReturnExactUserCount() throws Exception {
        dao.addUser(user);

        User user1 = new User();
        user1.setEmail("test name");
        dao.addUser(user1);

        final int page = 1;
        final int pageSize = 5;
        List<User> userList = dao.getUsersByPage(page, pageSize);

        final int expectedClientCount = 2;
        final String errorMessage = "Expected and actual user size are different";
        assertEquals(errorMessage, expectedClientCount, userList.size());
    }

    @Test
    public void afterUpdateUserGetClientByIdShouldReturnCorrespondClient() throws Exception {
        dao.addUser(user);
        user.setEmail("new test name");
        dao.updateUser(user);

        Optional<User> actualUser = dao.getUserById(user.getId());

        final String errorMessage = "Expected and actual user are different";
        assertEquals(errorMessage, user, actualUser.orElse(new User()));
    }

    @Test
    public void afterDeleteUserGetClientCountShouldReturnCorrespondClientCount() throws Exception {
        dao.addUser(user);
        dao.deleteUser(user);
        final int expectedClientCount = 0;
        final int actualClientCount = dao.getUserCount();

        final String errorMessage = "Expected and actual user size are different";
        assertEquals(errorMessage, expectedClientCount, actualClientCount);
    }

    @Test
    public void deleteUserWithNoIdShouldNotThrowException() throws Exception {
        dao.deleteUser(user);
    }

    @Test
    public void getUserByEmailShouldReturnCorrespondUser() throws Exception {
        final String email = "test@gmail.com";
        User testUser = new User();
        testUser.setEmail(email);
        dao.addUser(testUser);

        Optional<User> user = dao.getUserByEmail(email);

        final String errorMessage = "Expected and actual emails are different";
        assertEquals(email, user.orElse(new User()).getEmail());
    }

    @Test
    public void afterCreateUserWithOneRoleShouldReturnCorrespondUserTest() throws Exception {
        User testUser = new User();
        final String testUserEmail = "test@mail.ru";

        testUser.setEmail(testUserEmail);

        Set<User.Role> roles = new HashSet<>();
        roles.add(User.Role.ADMIN);
        testUser.setRoles(roles);

        dao.addUser(testUser);
        Optional<User> resultUser = dao.getUserByEmail(testUserEmail);

        final String errorMessage = "Expected and actual users are different";
        assertEquals(errorMessage, testUser, resultUser.orElse(new User()));
    }

    @Test
    public void afterCreateUserWithMultipleRolesShouldReturnCorrespondUserTest() throws Exception {
        User testUser = new User();
        final String testUserEmail = "test@mail.ru";

        testUser.setEmail(testUserEmail);

        Set<User.Role> roles = new HashSet<>();
        roles.add(User.Role.ADMIN);
        roles.add(User.Role.SYSADMIN);
        roles.add(User.Role.MANAGER);
        testUser.setRoles(roles);

        dao.addUser(testUser);
        Optional<User> resultUser = dao.getUserByEmail(testUserEmail);

        final String errorMessage = "Expected and actual users are different";
        assertEquals(errorMessage, testUser, resultUser.orElse(new User()));
    }
}