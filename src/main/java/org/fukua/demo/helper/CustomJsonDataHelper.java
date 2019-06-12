package org.fukua.demo.helper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomJsonDataHelper<T> {

    // Class made to return as valid empty Json
    @JsonSerialize
    private class EmptyJsonResponse { }

    // If object is empty returns valid empty Json
    public ResponseEntity<Object> buildResponse(Object object) {
        if (object == null) {
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }
}
