package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.ClientDao;
import com.itechart.trucking.domain.Client;
import com.itechart.trucking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Gleb Streltsov
 * @version 1.0
 * @since 2017-11-19
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao dao;

    @Override
    public Optional<Client> getClient(Integer id) {
        return dao.getClientById(id);
    }

    @Override
    public List<Client> getClientsByName(String namePattern) {
        return dao.findClientsByName(namePattern);
    }

    @Override
    public List<Client> getClientsByPage(int pageNumber, int pageSize) {
        return dao.getClientsByPage(pageNumber, pageSize);
    }

    @Override
    public int getClientCount() {
        return dao.getClientCount();
    }

    @Override
    public Client addClient(Client client) {
        return dao.addClient(client);
    }

    @Override
    public void updateClient(Client client) {
        dao.updateClient(client);
    }

    @Override
    public void deleteClient(Client client) {
        dao.deleteClient(client);
    }
}
