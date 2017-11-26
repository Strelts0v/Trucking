package com.itechart.trucking.service;

import com.itechart.trucking.domain.Client;

import java.util.List;
import java.util.Optional;

/**
 * @author Gleb Streltsov
 * @version 1.0
 * @since 2017-11-19
 */
public interface ClientService {

    /**
     * gets client by id
     * @param id - unique id client
     * @return Optional client object
     */
    Optional<Client> getClient(Integer id);

    /**
     * gets list of clients
     * @param namePattern - part of client name for searching
     * @return list of found clients objects
     */
    List<Client> getClientsByName(String namePattern);

    /**
     * Returns all instances of the {@link Client} by the given page number and size.
     * @param pageNumber - offset of instances
     * @param pageSize - count of instances in page
     * @return list of clients objects per page
     */
    List<Client> getClientsByPage(int pageNumber, int pageSize);

    /**
     * gets count of clients
     * @return count of client objects in storage
     */
    int getClientCount();

    /**
     * adds new client
     * @param client - new client object
     * @return added client object with unique id
     */
    Client addClient(Client client);

    /**
     * updates existed client
     * @param client - new client with id, if client doesn't contain
     *                 id, client object will be saved
     */
    void updateClient(Client client);

    /**
     * deletes existed client
     * @param client - existed client with id
     */
    void deleteClient(Client client);
}
