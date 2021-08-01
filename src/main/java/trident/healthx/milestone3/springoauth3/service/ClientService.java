package trident.healthx.milestone3.springoauth3.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.stereotype.Service;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.domain.security.ClientGrants;
import trident.healthx.milestone3.springoauth3.repository.security.ClientGrantsRepository;
import trident.healthx.milestone3.springoauth3.repository.security.ClientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;
    private ClientGrantsRepository clientGrantsRepository;

    public ClientService(ClientRepository clientRepository,PasswordEncoder passwordEncode,ClientGrantsRepository clientGrantsRepository){
        this.clientRepository=clientRepository;
        this.passwordEncoder=passwordEncode;
        this.clientGrantsRepository=clientGrantsRepository;
    }

    public void addClient( Client client){
        Optional<Client> clientOptional = clientRepository.findClientByClientId(client.getClientId());

        if(clientOptional.isEmpty()){
            client.setSecret(passwordEncoder.encode(client.getSecret()));
            clientRepository.saveAndFlush(client);


            client.getClientGrants().forEach(clientGrant -> clientGrant.setClient(clientRepository.findClientByClientId(client.getClientId()).get()));


            clientGrantsRepository.saveAll(client.getClientGrants());

        }else{
            throw new ClientAlreadyExistsException("Client already exists");
        }
    }

    public List<Client> getAllClients(){

        return clientRepository.findAll();

    }



}
