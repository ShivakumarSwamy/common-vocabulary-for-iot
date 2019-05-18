package de.uni.stuttgart.ipvs.tm.topics.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;
import de.uni.stuttgart.ipvs.tm.topics.form.TopicFormControlErrorException;
import de.uni.stuttgart.ipvs.tm.topics.persistence.TopicRepositoryException;

import static de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils.defaultInternalServerError;

@ControllerAdvice
@Slf4j
public class GlobalControllerTopicsExceptionHandler {


    @ExceptionHandler
    public ResponseEntity handlerTopicsRepositoryException(TopicRepositoryException failedTopicRepositoryRequest) {
        log.error("FAILED TOPIC REPOSITORY REQUEST", failedTopicRepositoryRequest);
        return defaultInternalServerError();
    }

    @ExceptionHandler
    public ResponseEntity handlerTopicFormControlErrorException(TopicFormControlErrorException failedTopicFormControl) {
        log.error("FAILED TOPIC FORM CONTROL", failedTopicFormControl);
        return ResponseEntityUtils
                .getResponseEntityWithMessageKey(HttpStatus.BAD_REQUEST, failedTopicFormControl.getMessage());
    }

}
