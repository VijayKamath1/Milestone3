package trident.healthx.milestone3.springoauth3.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import trident.healthx.milestone3.springoauth3.domain.security.Authority;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.domain.security.ClientGrants;
import trident.healthx.milestone3.springoauth3.domain.security.User;
import trident.healthx.milestone3.springoauth3.repository.security.AuthorityRepository;
import trident.healthx.milestone3.springoauth3.repository.security.ClientRepository;
import trident.healthx.milestone3.springoauth3.repository.security.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ClientRepository clientRepository;

private void loadSecurityData(){

    System.out.println(
            " Going to load security data");

    Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
    Authority user = authorityRepository.save(Authority.builder().role("USER").build());
    Authority customer = authorityRepository.save(Authority.builder().role("CUSTOMER").build());

    userRepository.save(User.builder().username("spring").password(passwordEncoder.encode("guru")).authority(admin).build());

    userRepository.save(User.builder().username("user").password(passwordEncoder.encode("password")).authority(admin).build());

    userRepository.save(User.builder().username("scott").password(passwordEncoder.encode("tiger")).authority(admin).build());

    ClientGrants clientGrantsRead = ClientGrants.builder().grantType("Read").build();
    ClientGrants clientGrantsWrite = ClientGrants.builder().grantType("Write").build();

    clientRepository.save(Client.builder().clientId("1").clientGrants(Arrays.asList(clientGrantsRead)).secret("Test123").build());
    clientRepository.save(Client.builder().clientId("2").clientGrants(Arrays.asList(clientGrantsWrite)).secret("Test321").build());





    System.out.println("Users Loaded :"+userRepository.count());
}

@Override
    public void run(String... args) throws Exception{
    System.out.println("inside user loader data "+authorityRepository+" user "+userRepository);
    if(authorityRepository!=null && authorityRepository.count()==0){
        loadSecurityData();
    }
}



}
