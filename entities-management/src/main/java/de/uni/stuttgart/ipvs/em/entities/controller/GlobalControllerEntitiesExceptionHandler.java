package de.uni.stuttgart.ipvs.em.entities.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.uni.stuttgart.ipvs.em.entities.form.EntityFormControlErrorException;
import de.uni.stuttgart.ipvs.em.entities.persistence.EntityRepositoryException;
import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;

import static de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils.defaultInternalServerError;

@ControllerAdvice
@Slf4j
public class GlobalControllerEntitiesExceptionHandler {


    @ExceptionHandler
    public ResponseEntity handlerEntityRepositoryException(EntityRepositoryException failedEntityRepositoryRequest) {
        log.error("FAILED ENTITY REPOSITORY REQUEST", failedEntityRepositoryRequest);
        return defaultInternalServerError();
    }

    @ExceptionHandler
    public ResponseEntity handlerEntityFormControlErrorException(
            EntityFormControlErrorException failedEntityFormControl) {
        
        log.error("FAILED ENTITY FORM CONTROL", failedEntityFormControl);
        return ResponseEntityUtils
                .getResponseEntityWithMessageKey(HttpStatus.BAD_REQUEST, failedEntityFormControl.getMessage());
    }

}
