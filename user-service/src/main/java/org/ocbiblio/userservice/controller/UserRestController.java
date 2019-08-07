package org.ocbiblio.userservice.controller;

import org.ocbiblio.userservice.dao.UserRepository;
import org.ocbiblio.userservice.entities.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserRestController {

    private UserRepository userRepository;
    UserRestController(UserRepository userRepository) { this.userRepository = userRepository; }

    @RequestMapping(value = "/LoadUser/")
    public User user (@RequestParam(name="pseudo", defaultValue = "")String pseudo,
                      @RequestParam(name="password", defaultValue = "")String password){
        return userRepository.findUserByPseudoAndPassword(pseudo, password);
    }
}
