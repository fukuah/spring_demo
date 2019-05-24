package org.fukua.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some items of geologicalClassList in section lack required field \"code\"")
public class GeologicalClassCodeIsNotSetException extends RuntimeException {
}
