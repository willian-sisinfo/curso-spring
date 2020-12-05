package br.com.udemy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJWTAuthenticationException extends AuthenticationException {

    public InvalidJWTAuthenticationException(String exception) {
        super(exception);
    }
}
