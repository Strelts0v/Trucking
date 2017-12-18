package com.itechart.trucking.controller;

import com.itechart.trucking.domain.Client;
import com.itechart.trucking.service.ClientService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.itechart.trucking.util.Constants.NUMBER_REGEX;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @GetMapping("/count")
    public int getClientCount() {
        log.debug("REST request to get client count");
        return service.getClientCount();
    }

    @GetMapping("/get_clients/{page:" + NUMBER_REGEX + "}/{size:" + NUMBER_REGEX + "}" )
    public ResponseEntity<List<Client>> getClients(@PathVariable int page, @PathVariable int size){
        log.debug("REST request for clients by page {0} size {1}", page, size);
        List<Client> clients = service.getClientsByPage(page, size);
        log.debug("Returning clients by page {0} size {1}: {2}", page, size, clients);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/add_client")
    public ResponseEntity<Client> addClient (@RequestBody Client client){
        log.debug("REST request for adding a new Client: {}", client);
        Client clientWithId = service.addClient(client);
        log.debug("Added client: " + clientWithId);
        return new ResponseEntity<>(clientWithId, HttpStatus.CREATED);
    }

    @PutMapping("/update_client")
    public ResponseEntity<Client> updateClient (@RequestBody Client client){
        log.debug("REST request for updating client: {}", client);
        service.updateClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/delete_client")
    public ResponseEntity<Void> deleteClient (@RequestBody Client client){
        log.debug("REST request for deleting client: {}", client);
        service.deleteClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
