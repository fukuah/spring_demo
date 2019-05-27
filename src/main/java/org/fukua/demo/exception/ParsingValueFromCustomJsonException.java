package org.fukua.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request body of Json have not provided required attribute. Json example: {\"attribute_name\": \"attribute_value\"}")
public class ParsingValueFromCustomJsonException extends RuntimeException{
}
