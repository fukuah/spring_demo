package org.fukua.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No section to update was found.")
public class SectionIsNotFoundException extends RuntimeException {
}
