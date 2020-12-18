package com.sbkftw.postacom.rest;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbkftw.postacom.model.User;
import com.sbkftw.postacom.persistence.UserRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable("userId") String id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
