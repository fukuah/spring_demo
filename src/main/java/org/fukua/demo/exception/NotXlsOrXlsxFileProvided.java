package org.fukua.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "File is not of xls or xlsx type.")
public class NotXlsOrXlsxFileProvided extends RuntimeException{
}
