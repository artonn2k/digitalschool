package com.zerogravitysolutions.digitalschool.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(StudentNotFoundException.class)
    ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException ex){

        logger.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(InstructorNotFoundException.class)
    ResponseEntity<ErrorResponse> handleInstructorNotFoundException(InstructorNotFoundException iex){

        logger.info(iex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND, iex.getMessage()));
    }


    @ExceptionHandler(GroupNotFoundException.class)
    ResponseEntity<ErrorResponse> handleGroupNotFoundException(GroupNotFoundException gex){

        logger.info(gex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND, gex.getMessage()));
    }

    @ExceptionHandler(TrainingNotFoundException.class)
    ResponseEntity<ErrorResponse> handleTrainingNotFoundExcpetion(TrainingNotFoundException tex){

        logger.info(tex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND, tex.getMessage()));
    }
}
