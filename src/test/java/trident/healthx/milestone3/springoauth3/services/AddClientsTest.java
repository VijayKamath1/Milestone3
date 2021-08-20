package trident.healthx.milestone3.springoauth3.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.domain.security.ClientGrants;
import trident.healthx.milestone3.springoauth3.repository.security.ClientRepository;
import trident.healthx.milestone3.springoauth3.service.ClientService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

    @SpringBootTest
    public class AddClientsTest {

        @Autowired
        private ClientService clientService;

        @MockBean
        private ClientRepository clientRepository;

        @MockBean
        private PasswordEncoder passwordEncoder;

        @Test
        @DisplayName("Considering the client doesn't exist in the database " +
                "assert that the service adds the client record.")
        public void addClientWhenClientDoesntExistTest() {
            Client c = new Client();
            c.setClientId("client");
            c.setSecret("secret");
            c.setClientGrants(List.of(new ClientGrants()));

            when(clientRepository.findClientByClientId(c.getClientId()))
                    .thenReturn(Optional.empty());

            clientService.addClient(c);

            verify(passwordEncoder).encode("secret");
            verify(clientRepository).save(c);
        }

        @Test
        @DisplayName("Considering the client already exists in the database " +
                "assert that the service throws an exception and " +
                "doesn't add the client record.")
        public void addClientWhenClientAlreadyExistsTest() {
            Client c = new Client();
            c.setClientId("client");
            c.setSecret("secret");

            when(clientRepository.findClientByClientId(c.getClientId()))
                    .thenReturn(Optional.of(c));

            assertThrows(ClientAlreadyExistsException.class
                    , () -> clientService.addClient(c));

            verify(passwordEncoder, never()).encode(c.getSecret());
            verify(clientRepository, never()).save(c);
        }
}
