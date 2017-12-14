package com.itechart.trucking.controller;

import com.itechart.trucking.domain.Client;
import com.itechart.trucking.service.ClientService;
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
@RequestMapping("/api/clients")
@PreAuthorize("isFullyAuthenticated()")
public class ClientController {

    private final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @RequestMapping("/count")
    public int getClientCount() {
        return service.getClientCount();
    }

    @RequestMapping("/get_clients")
    public ResponseEntity<List<Client>> getClients(
            @RequestParam(value="page", defaultValue="1") String page,
            @RequestParam(value="pageSize", defaultValue = "20") String pageSize){
        int pageNumber = Integer.parseInt(page);
        int clientCount = Integer.parseInt(pageSize);
        List<Client> clients = service.getClientsByPage(pageNumber, clientCount);
        log.info("return clients by page " + page + " pageSize " + pageSize + ": " + clients);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @RequestMapping("/add_client")
    public ResponseEntity<Client> addClient (@RequestParam(value="client") Client client){
        Client clientWithId = service.addClient(client);
        log.info("added client: " + clientWithId);
        return new ResponseEntity<>(clientWithId, HttpStatus.OK);
    }

    @RequestMapping("/update_client")
    public ResponseEntity<Void> updateClient (@RequestParam(value="client") Client client){
        service.updateClient(client);
        log.info("updated client: " + client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/delete_client")
    public ResponseEntity<Void> deleteClient (@RequestParam(value="client") Client client){
        service.deleteClient(client);
        log.info("deleted client: " + client);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
