package br.com.validatepassword.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordRequestDTO {

    @Size(min = 9, message = "A senha deve conter no mínimo 9 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+]).*", message = "A senha deve conter ao menos 1 dígito, 1 caracter minúsculo, 1 maiúsculo e 1 especial")
    private String password;
}
