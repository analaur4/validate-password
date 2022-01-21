package br.com.validatepassword.controllers;

import br.com.validatepassword.dtos.PasswordRequestDTO;
import br.com.validatepassword.dtos.PasswordResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@SpringBootTest
class PasswordControllerTest {

    private final String PASSWORD_VALID = "AbTp9!foa";

    @Autowired
    private PasswordController controller;

    @Test
    void validatePassword() throws Exception {
        PasswordRequestDTO request = PasswordRequestDTO.builder().password(PASSWORD_VALID).build();
        ResponseEntity result = controller.validatePassword(request);

        PasswordResponseDTO response = PasswordResponseDTO.builder().isValid(true).build();

        assertEquals(ResponseEntity.ok().body(response), result);
    }

}