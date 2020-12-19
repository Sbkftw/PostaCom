package com.sbkftw.postacom.rest;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbkftw.postacom.model.User;
import com.sbkftw.postacom.persistence.UserRepository;
import com.sbkftw.postacom.rest.dto.UserDTO;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper    modelMapper;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable("userId") Integer id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody @Valid UserDTO user) {
        userRepository.save(convertToEntity(user));
    }

    @PutMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable("userId") Integer id, @RequestBody @Valid UserDTO user) {
        User updatedUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(convertToEntity(user), updatedUser);
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
