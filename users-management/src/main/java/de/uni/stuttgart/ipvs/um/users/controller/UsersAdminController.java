package de.uni.stuttgart.ipvs.um.users.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.um.response.ResultsSet;
import de.uni.stuttgart.ipvs.um.users.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/admin/users")
@Api(tags = "Users")
public class UsersAdminController {

    private final UserDetailsServiceImpl userDetailsService;

    public UsersAdminController(UserDetailsServiceImpl userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    @ApiOperation("Get all users")
    public ResultsSet getAllUsers() {
        return this.userDetailsService.findAllUsers();
    }

}
