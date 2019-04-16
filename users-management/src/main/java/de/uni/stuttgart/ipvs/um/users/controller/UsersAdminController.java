package de.uni.stuttgart.ipvs.um.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.results.VariableBinding;
import de.uni.stuttgart.ipvs.um.users.persistence.UserRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/accounts")
public class UsersAdminController {

    private final UserRepository userRepository;

    public UsersAdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Map<String, List<Map<String, VariableBinding>>> getAllUsers() {
        return this.userRepository.findAllUsers();
    }

}
