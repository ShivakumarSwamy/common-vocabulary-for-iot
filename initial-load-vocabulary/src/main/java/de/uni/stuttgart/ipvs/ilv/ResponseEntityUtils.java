package de.uni.stuttgart.ipvs.ilv;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseEntityUtils {

    private ResponseEntityUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    public static ResponseEntity defaultInternalServerError() {
        return getResponseEntityWithMessageKey(
                HttpStatus.INTERNAL_SERVER_ERROR, "Sorry internal server error, please try again later");
    }

    public static ResponseEntity getResponseEntityWithMessageKey(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                .body(Map.of("message", message));
    }
}
