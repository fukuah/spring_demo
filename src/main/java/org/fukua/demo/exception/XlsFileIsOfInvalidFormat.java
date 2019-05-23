package org.fukua.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Xls file is empty or of invalid format.")
public class XlsFileIsOfInvalidFormat extends RuntimeException {
}
