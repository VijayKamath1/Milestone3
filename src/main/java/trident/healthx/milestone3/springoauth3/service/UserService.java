package trident.healthx.milestone3.springoauth3.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.stereotype.Service;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.domain.security.User;
import trident.healthx.milestone3.springoauth3.exception.UserAlreadyExistsException;
import trident.healthx.milestone3.springoauth3.repository.security.UserRepository;

import java.util.*;

@Service
public class UserService {

    private  UserRepository userRepository;
    private  PasswordEncoder passwordEnc;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEnc){
        this.userRepository=userRepository;
        this.passwordEnc=passwordEnc;
    }


    public void addUser( User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if(userOptional.isEmpty()){
            user.setPassword(passwordEnc.encode(user.getPassword()));
            user.getAuthorities().forEach(gt-> gt.setUsers( user));
            userRepository.save(user);
        }else{
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


}
