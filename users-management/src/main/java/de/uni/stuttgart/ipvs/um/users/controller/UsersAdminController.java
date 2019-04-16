package de.uni.stuttgart.ipvs.um.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.um.response.ResultsSet;
import de.uni.stuttgart.ipvs.um.users.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/admin/users")
public class UsersAdminController {

    private final UserDetailsServiceImpl userDetailsService;

    public UsersAdminController(UserDetailsServiceImpl userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public ResultsSet getAllUsers() {
        return this.userDetailsService.findAllUsers();
    }

}
