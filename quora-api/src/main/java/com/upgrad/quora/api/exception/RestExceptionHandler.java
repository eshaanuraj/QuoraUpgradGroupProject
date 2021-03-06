package com.upgrad.quora.api.exception;

import com.upgrad.quora.api.model.ErrorResponse;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    /**Handles SignUpRestrictedException. Triggered when duplicate username and email found during signup.
     *
     * @param exception SignUpRestrictedException
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(SignUpRestrictedException.class)
    public ResponseEntity<ErrorResponse> signUpRestrictedException(SignUpRestrictedException exception, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()), HttpStatus.CONFLICT);
    }

    /** Handles the AutenticationFailedException. This is triggered when user failed to authenticate himself. Bad user name or Password.
     * AuthenticationFailedException - HttpStatus.UNAUTHORIZED
     *
     * @param authFailedException
     * @param request WebRequest
     * @return ResponseEntity
     */

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> AuthenticationFailedException(AuthenticationFailedException authFailedException, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(authFailedException.getCode()).message(authFailedException.getErrorMessage()), HttpStatus.UNAUTHORIZED);
    }
}
