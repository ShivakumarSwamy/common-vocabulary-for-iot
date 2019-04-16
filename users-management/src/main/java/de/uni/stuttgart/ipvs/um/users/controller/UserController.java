package de.uni.stuttgart.ipvs.um.users.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;
import de.uni.stuttgart.ipvs.um.users.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserCreateDTO userCreateDTO) {
        String uuidUser = this.userDetailsServiceImpl.createUser(userCreateDTO);

        var newUserLocationUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuidUser)
                .toUri();

        return ResponseEntity.created(newUserLocationUri).build();
    }
}
