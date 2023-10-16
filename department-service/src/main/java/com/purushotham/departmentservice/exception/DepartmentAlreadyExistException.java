package com.purushotham.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentAlreadyExistException extends RuntimeException{
    private String message;
    public DepartmentAlreadyExistException(String message){
        super(message);
    }

}
