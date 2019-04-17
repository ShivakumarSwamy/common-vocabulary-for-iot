package de.uni.stuttgart.ipvs.um.users.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;
import de.uni.stuttgart.ipvs.um.users.persistence.UserRepositoryException;

@ControllerAdvice
@Slf4j
public class UserControllerAdvice {

    @ExceptionHandler
    public ResponseEntity handleUserRepositoryException(UserRepositoryException failedSparql) {
        log.error("FAILED USER REPOSITORY SPARQL", failedSparql);
        return ResponseEntityUtils.defaultInternalServerError();
    }
}
