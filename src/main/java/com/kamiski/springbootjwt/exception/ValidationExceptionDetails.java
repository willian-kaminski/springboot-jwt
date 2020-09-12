package com.kamiski.springbootjwt.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetail{

    private String field;
    private String fieldMessage;

}
