package com.review.gradle_j11_sb255.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ConflictException extends RuntimeException {

    String code, message;
    HttpStatus status;

}
