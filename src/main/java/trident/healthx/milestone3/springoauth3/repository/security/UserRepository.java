package trident.healthx.milestone3.springoauth3.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import trident.healthx.milestone3.springoauth3.domain.security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername( String username);
}