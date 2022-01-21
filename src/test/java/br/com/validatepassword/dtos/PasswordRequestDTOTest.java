package br.com.validatepassword.dtos;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.Assert.*;

@SpringBootTest
public class PasswordRequestDTOTest {

    private final String PASSWORD_WITHOUT_DIGIT = "AbTpe!foa";
    private final String PASSWORD_WITHOUT_LOWERCASE = "ABTP9!FOA";
    private final String PASSWORD_WITHOUT_UPPERCASE = "abtp9!foa";
    private final String PASSWORD_WITHOUT_SIZE_VALID = "AbTp9!fo";
    private final String PASSWORD_WITHOUT_SPECIAL_CHARACTER = "AbTp9efok";
    private final String MESSAGE_PATTERN = "A senha deve conter ao menos 1 dígito, 1 caracter minúsculo, 1 maiúsculo e 1 especial";
    private final String MESSAGE_SIZE = "A senha deve conter no mínimo 9 caracteres";

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void isInvalidSize() {
        PasswordRequestDTO request = PasswordRequestDTO.builder().password(PASSWORD_WITHOUT_SIZE_VALID).build();
        Set<ConstraintViolation<PasswordRequestDTO>> violations = validator.validate(request);
        ConstraintViolation<PasswordRequestDTO> violation = violations.iterator().next();

        assertEquals(MESSAGE_SIZE, violation.getMessage());
    }

    @Test
    public void isInvalidPatternDigit() {
        PasswordRequestDTO request = PasswordRequestDTO.builder().password(PASSWORD_WITHOUT_DIGIT).build();
        Set<ConstraintViolation<PasswordRequestDTO>> violations = validator.validate(request);
        ConstraintViolation<PasswordRequestDTO> violation = violations.iterator().next();

        assertEquals(MESSAGE_PATTERN, violation.getMessage());
    }

    @Test
    public void isInvalidPatternLowercase() {
        PasswordRequestDTO request = PasswordRequestDTO.builder().password(PASSWORD_WITHOUT_LOWERCASE).build();
        Set<ConstraintViolation<PasswordRequestDTO>> violations = validator.validate(request);
        ConstraintViolation<PasswordRequestDTO> violation = violations.iterator().next();

        assertEquals(MESSAGE_PATTERN, violation.getMessage());
    }

    @Test
    public void isInvalidPatternUppercase() {
        PasswordRequestDTO request = PasswordRequestDTO.builder().password(PASSWORD_WITHOUT_UPPERCASE).build();
        Set<ConstraintViolation<PasswordRequestDTO>> violations = validator.validate(request);
        ConstraintViolation<PasswordRequestDTO> violation = violations.iterator().next();

        assertEquals(MESSAGE_PATTERN, violation.getMessage());
    }

    @Test
    public void isInvalidPatternSpecialCharacter() {
        PasswordRequestDTO request = PasswordRequestDTO.builder().password(PASSWORD_WITHOUT_SPECIAL_CHARACTER).build();
        Set<ConstraintViolation<PasswordRequestDTO>> violations = validator.validate(request);
        ConstraintViolation<PasswordRequestDTO> violation = violations.iterator().next();

        assertEquals(MESSAGE_PATTERN, violation.getMessage());
    }
}
