package trident.healthx.milestone3.springoauth3.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import trident.healthx.milestone3.springoauth3.domain.security.Client;
import trident.healthx.milestone3.springoauth3.domain.security.User;
import trident.healthx.milestone3.springoauth3.service.ClientService;
import trident.healthx.milestone3.springoauth3.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

private final UserService userService;


    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping(path = "user",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){

        System.out.println("Inside post controller");
        userService.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){

        System.out.println("Inside get   controller");
        return userService.getAllUsers();
    }


}



