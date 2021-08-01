package trident.healthx.milestone3.springoauth3.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import trident.healthx.milestone3.springoauth3.domain.security.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findClientByClientId( String clientId);

}