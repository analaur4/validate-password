package br.com.validatepassword.controllers.handlers;

import br.com.validatepassword.dtos.PasswordResponseDTO;
import br.com.validatepassword.services.exceptions.PasswordInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PasswordResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        PasswordResponseDTO response = PasswordResponseDTO.builder()
                .isValid(false)
                .message(exception.getBindingResult().getFieldError().getDefaultMessage())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<PasswordResponseDTO> handlePasswordInvalidException(PasswordInvalidException exception) {
        PasswordResponseDTO response = PasswordResponseDTO.builder()
                .isValid(false)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}
