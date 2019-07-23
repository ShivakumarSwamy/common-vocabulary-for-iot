package de.uni.stuttgart.ipvs.um.users.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.um.response.ResultsSet;
import de.uni.stuttgart.ipvs.um.users.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/admin/users")
@Api(tags = "Admin Users")
public class UsersAdminController {

    private final UserDetailsServiceImpl userDetailsService;

    public UsersAdminController(UserDetailsServiceImpl userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A array list of map with username, role and id in results key"),
            @ApiResponse(code = 400, message = "A JSON Error Message with value present in message key of a JSON object," +
                    "Message consists of form error or user already exists error"),
            @ApiResponse(code = 500, message = "Sorry internal server error, please try again later")
    })
    public ResultsSet getAllUsers() {
        return this.userDetailsService.findAllUsers();
    }

}
