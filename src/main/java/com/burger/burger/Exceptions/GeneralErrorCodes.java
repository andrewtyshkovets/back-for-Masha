package com.burger.burger.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GeneralErrorCodes implements IApiError {
    UNEXPECTED_EXCEPTION("G00001", "Unexpected exception"),
    WRONG_REQUEST_BODY_STRUCTURE("G00002","Illegal service request body");
    private final String code;
    private final String message;
}
