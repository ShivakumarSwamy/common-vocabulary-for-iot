package de.uni.stuttgart.ipvs.um.users.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;
import de.uni.stuttgart.ipvs.um.form.UserFormControlErrorException;
import de.uni.stuttgart.ipvs.um.response.ResultsSet;
import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;
import de.uni.stuttgart.ipvs.um.users.persistence.UserDetailsImpl;
import de.uni.stuttgart.ipvs.um.users.service.UserDetailsServiceImpl;
import de.uni.stuttgart.ipvs.um.users.service.UserServiceException;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Api(tags = "All Users")
public class UsersController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UsersController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping
    @ApiOperation(value = "Create a user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "On Success of a new user created"),
            @ApiResponse(code = 400, message = "A JSON Error Message with value present in message key of a JSON object," +
                    "Message consists of form error or user already exists error"),
            @ApiResponse(code = 500, message = "Sorry internal server error, please try again later")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @ApiParam(value = "DTO object required to create a user", required = true)
            @RequestBody UserCreateDTO userCreateDTO) {
        this.userDetailsServiceImpl.createUser(userCreateDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get your user information, needs authentication")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A Map with username, role and id in results key"),
            @ApiResponse(code = 400, message = "A JSON Error Message with value present in message key of a JSON object," +
                    "Message consists of form error or user already exists error"),
            @ApiResponse(code = 500, message = "Sorry internal server error, please try again later")
    })
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
