package trident.healthx.milestone3.springoauth3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @PostMapping
    public void addClient( @RequestBody Client client){
        clientService.addClient(client);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }


}
