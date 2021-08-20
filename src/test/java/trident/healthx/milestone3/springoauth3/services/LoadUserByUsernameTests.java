package trident.healthx.milestone3.springoauth3.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import trident.healthx.milestone3.springoauth3.domain.security.User;
import trident.healthx.milestone3.springoauth3.repository.security.UserRepository;
import trident.healthx.milestone3.springoauth3.service.JpaUserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest

public class LoadUserByUsernameTests {


    @Autowired
    private JpaUserDetails userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Considering that the client doesn't exist in the database," +
            "test that the method throws ClientRegistrationException.")
    public void loadUserByUserNameUserDoesntExistTest() {
        String username = "client";

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(username));
    }

    @Test
    @DisplayName("Considering that the client exists in the database," +
            "test that the method returns a valid ClientDetails instance.")
    public void loadClientByClientIdTest() {
        User user = new User();
        user.setUsername("scott");

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        System.out.println(" when comment over ");
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        assertEquals(userDetails.getUsername(), user.getUsername());
    }
}
