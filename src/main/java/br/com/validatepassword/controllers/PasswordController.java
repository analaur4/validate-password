package br.com.validatepassword.controllers;

import br.com.validatepassword.dtos.PasswordRequestDTO;
import br.com.validatepassword.dtos.PasswordResponseDTO;
import br.com.validatepassword.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/validate-password")
public class PasswordController {

    private final PasswordService service;

    @PostMapping
    public ResponseEntity validatePassword(@Valid @RequestBody PasswordRequestDTO password) throws Exception {
        boolean result = service.isValid(password.getPassword());
        PasswordResponseDTO dto = PasswordResponseDTO.builder().isValid(result).build();

        return ResponseEntity.ok().body(dto);

    }
}
