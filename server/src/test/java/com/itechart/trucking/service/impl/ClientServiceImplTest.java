package com.itechart.trucking.service.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.Client;
import com.itechart.trucking.service.ClientService;
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
 * Test class for the ClientServiceImpl.
 *
 * @see ClientServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class ClientServiceImplTest {

    @Autowired
    private ClientService service;

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
        Optional<Client> client = service.getClient(invalidClientId);

        final String errorMessage = "Expected empty optional client object";
        Assert.assertFalse(errorMessage, client.isPresent());
    }

    @Test
    public void getClientByIdShouldReturnCorrespondClient() throws Exception {
        Client expectedClient = service.addClient(client);
        final int addedClientId = expectedClient.getId();
        Optional<Client> actualClient = service.getClient(addedClientId);

        final String errorMessage = "Expected and actual client are different";
        assertEquals(errorMessage, expectedClient, actualClient.orElse(new Client()));
    }

    @Test
    public void findClientsByNameShouldReturnExactClientCount() throws Exception {
        int id = service.addClient(client).getId();

        Client client1 = new Client();
        client1.setName("test name");
        service.addClient(client1);

        Client client2 = new Client();
        client2.setName("test");
        service.addClient(client2);

        final String searchNamePattern = "name";
        List<Client> clientList = service.getClientsByName(searchNamePattern);

        final int expectedClientCount = 2;
        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedClientCount, clientList.size());
    }

    @Test
    public void getClientsByExistedPageShouldReturnExactClientCount() throws Exception {
        service.addClient(client);

        final int page = 1;
        final int pageSize = 20;
        List<Client> clientList = service.getClientsByPage(page, pageSize);

        final int expectedClientCount = 1;
        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedClientCount, clientList.size());
    }

    @Test
    public void afterUpdateClientGetClientByIdShouldReturnCorrespondClient() throws Exception {
        service.addClient(client);
        client.setName("new test name");
        service.updateClient(client);

        Optional<Client> actualClient = service.getClient(client.getId());

        final String errorMessage = "Expected and actual client are different";
        assertEquals(errorMessage, client, actualClient.orElse(new Client()));
    }

    @Test
    public void afterDeleteClientGetClientCountShouldReturnCorrespondClientCount() throws Exception {
        service.addClient(client);
        service.deleteClient(client);

        Client client1 = new Client();
        client1.setName("test name");
        service.addClient(client1);

        final int expectedClientCount = 1;
        final int actualClientCount = service.getClientCount();

        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedClientCount, actualClientCount);
    }

    @Test
    public void deleteClientWithNoIdShouldNotThrowException() throws Exception {
        service.deleteClient(client);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteClientWithNotExistedIdShouldThrowException() throws Exception {
        client.setId(1);
        service.deleteClient(client);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteClientWithInvalidIdShouldThrowException() throws Exception {
        client.setId(Integer.MAX_VALUE);
        service.deleteClient(client);
    }
}