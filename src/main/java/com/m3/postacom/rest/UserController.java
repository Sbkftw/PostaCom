package com.m3.postacom.rest;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.m3.postacom.generated.api.UserApi;
import com.m3.postacom.generated.model.UserDTO;
import com.m3.postacom.model.User;
import com.m3.postacom.persistence.UserRepository;

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
        userRepository.save(convertEntoty(userDTO));
        return ResponseEntity.ok().build();
    }

    private User convertEntoty(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
