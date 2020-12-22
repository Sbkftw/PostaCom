package com.sbkftw.postacom.rest;

import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.sbkftw.postacom.generated.api.UserApi;
import com.sbkftw.postacom.generated.model.UserDTO;
import com.sbkftw.postacom.model.User;
import com.sbkftw.postacom.persistence.UserRepository;

@RestController
public class UserController implements UserApi {

    private final UserRepository userRepository;
    private final ModelMapper    modelMapper;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<Void> createUser(@Valid UserDTO userDTO) {
        userRepository.save(convertToEntity(userDTO));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
