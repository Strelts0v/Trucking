package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.ClientDao;
import com.itechart.trucking.domain.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the JpaClientDao.
 *
 * @see JpaClientDao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class JpaClientDaoTest {

    @Autowired
    private ClientDao dao;

    private Client client;

    private static final String CLIENT_NAME = "clientName";

    @Before
    public void setUp(){
        client = new Client();
        client.setName(CLIENT_NAME);
    }

    @Test
    public void getClientByIdShouldReturnNoClientTest() throws Exception {
        final int invalidClientId = 5;
        Optional<Client> client = dao.getClientById(invalidClientId);

        final String errorMessage = "Expected empty optional client object";
        Assert.assertFalse(errorMessage, client.isPresent());
    }

    @Test
    public void getClientByIdShouldReturnCorrespondClient() throws Exception {
        Client expectedClient = dao.addClient(client);
        final int addedClientId = expectedClient.getId();
        Optional<Client> actualClient = dao.getClientById(addedClientId);

        final String errorMessage = "Expected and actual client are different";
        assertEquals(errorMessage, expectedClient, actualClient.orElse(new Client()));
    }

    @Test
    public void findClientsByNameShouldReturnExactClientCount() throws Exception {
        dao.addClient(client);

        Client client1 = new Client();
        client1.setName("test name");
        dao.addClient(client1);

        Client client2 = new Client();
        client2.setName("test");
        dao.addClient(client2);

        final String searchNamePattern = "name";
        List<Client> clientList = dao.findClientsByName(searchNamePattern);

        final int expectedClientCount = 2;
        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedClientCount, clientList.size());
    }

    @Test
    public void getClientsByExistedPageShouldReturnExactClientCount() throws Exception {
        dao.addClient(client);

        Client client1 = new Client();
        client1.setName("test name");
        dao.addClient(client1);

        final int page = 1;
        final int pageSize = 20;
        List<Client> clientList = dao.getClientsByPage(page, pageSize);

        final int expectedClientCount = 2;
        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedClientCount, clientList.size());
    }

    @Test
    public void afterAddClientGetClientCountShouldReturnExactClientCount() throws Exception {
        dao.addClient(client);
        final int actualClientCount = dao.getClientCount();
        final int expectedClientCount = 1;

        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedClientCount, actualClientCount);
    }

    @Test
    public void afterUpdateClientGetClientByIdShouldReturnCorrespondClient() throws Exception {
        dao.addClient(client);
        client.setName("new test name");
        dao.updateClient(client);

        Optional<Client> actualClient = dao.getClientById(client.getId());

        final String errorMessage = "Expected and actual client are different";
        assertEquals(errorMessage, client, actualClient.orElse(new Client()));
    }

    @Test
    public void afterDeleteClientGetClientCountShouldReturnCorrespondClientCount() throws Exception {
        dao.addClient(client);
        dao.deleteClient(client);
        final int expectedClientCount = 0;
        final int actualClientCount = dao.getClientCount();

        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedClientCount, actualClientCount);
    }

    @Test
    public void deleteClientWithNoIdShouldNotThrowException() throws Exception {
        dao.deleteClient(client);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteClientWithNotExistedIdShouldThrowException() throws Exception {
        client.setId(1);
        dao.deleteClient(client);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteClientWithInvalidIdShouldThrowException() throws Exception {
        client.setId(Integer.MAX_VALUE);
        dao.deleteClient(client);
    }
}