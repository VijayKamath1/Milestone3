package trident.healthx.milestone3.springoauth3.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import trident.healthx.milestone3.springoauth3.domain.security.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}