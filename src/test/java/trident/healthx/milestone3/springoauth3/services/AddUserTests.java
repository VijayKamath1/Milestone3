package trident.healthx.milestone3.springoauth3.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import trident.healthx.milestone3.springoauth3.domain.security.Authority;
import trident.healthx.milestone3.springoauth3.domain.security.User;
import trident.healthx.milestone3.springoauth3.exception.UserAlreadyExistsException;
import trident.healthx.milestone3.springoauth3.repository.security.UserRepository;
import trident.healthx.milestone3.springoauth3.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddUserTests {


    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Considering the user doesn't exist in the database " +
            "assert that the service adds the new user record.")
    public void addUserWhenUserDoesntExistTest() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setAuthorities(Set.of(new Authority()));

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.empty());

        userService.addUser(user);

        verify(passwordEncoder).encode("password");
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Considering the user already exists in the database " +
            "assert that the service throws an exception and it doesn't " +
            "add the user record.")
    public void addUserWhenUserExistsTest() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setAuthorities(Set.of(new Authority()));

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class,
                () -> userService.addUser(user));

        verify(passwordEncoder, never()).encode("password");
        verify(userRepository, never()).save(user);
    }
}
