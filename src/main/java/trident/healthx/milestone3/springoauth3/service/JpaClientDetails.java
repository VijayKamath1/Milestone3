package trident.healthx.milestone3.springoauth3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.domain.security.ClientDetailsSecurityWrapper;
import trident.healthx.milestone3.springoauth3.exception.ClientNotExistsException;
import trident.healthx.milestone3.springoauth3.repository.security.ClientRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JpaClientDetails implements ClientDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId ( String clientId ) throws ClientRegistrationException {

        Client client = clientRepository.findClientByClientId(clientId).orElseThrow(()->{return new ClientNotExistsException("Client "+clientId+" not found");});

        return new ClientDetailsSecurityWrapper(client);
    }

    }

