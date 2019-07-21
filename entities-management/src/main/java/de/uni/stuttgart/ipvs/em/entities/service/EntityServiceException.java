package de.uni.stuttgart.ipvs.em.entities.service;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class EntityServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    EntityServiceException(HttpStatus httpStatus) {
        super();
        this.httpStatus = httpStatus;
    }
}
