package de.uni.stuttgart.ipvs.tm.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;
import de.uni.stuttgart.ipvs.tm.persistence.CviRepositoryException;

@ControllerAdvice
@Slf4j
public class CviControllerAdvice {

    @ExceptionHandler
    public ResponseEntity handleUserRepositoryException(CviRepositoryException failedSparql) {
        log.error("FAILED CVI REPOSITORY SPARQL", failedSparql);
        return ResponseEntityUtils.defaultInternalServerError();
    }
}
