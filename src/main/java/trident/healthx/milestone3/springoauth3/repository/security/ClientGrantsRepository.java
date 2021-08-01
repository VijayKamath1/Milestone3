package trident.healthx.milestone3.springoauth3.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.domain.security.ClientGrants;
import trident.healthx.milestone3.springoauth3.domain.security.ClientGrants;

import java.util.Optional;

public interface ClientGrantsRepository extends JpaRepository<ClientGrants, Integer> {

    //Optional<ClientGrants> findClientGrantsByClientId( String clientId);

}