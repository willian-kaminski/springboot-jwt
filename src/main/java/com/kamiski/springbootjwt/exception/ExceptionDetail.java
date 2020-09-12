package com.kamiski.springbootjwt.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetail {

    private String title;
    private int status;
    private String detail;
    private String developerMessage;
    private LocalDateTime timestamp;

}
