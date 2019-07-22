package de.uni.stuttgart.ipvs.um.users.controller;

import lombok.extern.slf4j.Slf4j;

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

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UsersController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreateDTO userCreateDTO) {
        this.userDetailsServiceImpl.createUser(userCreateDTO);
    }

    @GetMapping
    public ResultsSet getUserByUserIdInToken(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return this.userDetailsServiceImpl.findUserByUserId(userDetails.getId());
    }

    @ExceptionHandler
    public ResponseEntity handlerUserFormControlErrorException(UserServiceException failedServiceUser) {
        log.error("FAILED USER SERVICE", failedServiceUser);
        return ResponseEntityUtils.getResponseEntityWithMessageKey(HttpStatus.BAD_REQUEST, failedServiceUser.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handlerUserFormControlErrorException(UserFormControlErrorException failedUserFormControl) {
        log.error("FAILED USER FORM CONTROL", failedUserFormControl);
        return ResponseEntityUtils.getResponseEntityWithMessageKey(HttpStatus.BAD_REQUEST, failedUserFormControl.getMessage());
    }
}
