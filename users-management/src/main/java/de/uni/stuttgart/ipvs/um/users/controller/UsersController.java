package de.uni.stuttgart.ipvs.um.users.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;
import de.uni.stuttgart.ipvs.um.form.UserFormControlErrorException;
import de.uni.stuttgart.ipvs.um.response.ResultsSet;
import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;
import de.uni.stuttgart.ipvs.um.users.persistence.UserDetailsImpl;
import de.uni.stuttgart.ipvs.um.users.service.UserDetailsServiceImpl;
import de.uni.stuttgart.ipvs.um.users.service.UserServiceException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Api(tags = "Users")
public class UsersController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UsersController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping
    @ApiOperation("Create a user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @ApiParam(value = "DTO object required to create a user", required = true)
            @RequestBody UserCreateDTO userCreateDTO) {
        this.userDetailsServiceImpl.createUser(userCreateDTO);
    }

    @GetMapping
    @ApiOperation("Get your user information")
    public ResultsSet getUserByUserIdInToken(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return this.userDetailsServiceImpl.findUserByUserId(userDetails.getId());
    }

    @ExceptionHandler
    public ResponseEntity handlerUserServiceException(UserServiceException failedServiceUser) {
        log.error("FAILED USER SERVICE", failedServiceUser);
        return ResponseEntityUtils.getResponseEntityWithMessageKey(HttpStatus.BAD_REQUEST, failedServiceUser.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handlerUserFormControlErrorException(UserFormControlErrorException failedUserFormControl) {
        log.error("FAILED USER FORM CONTROL", failedUserFormControl);
        return ResponseEntityUtils.getResponseEntityWithMessageKey(HttpStatus.BAD_REQUEST, failedUserFormControl.getMessage());
    }
}
