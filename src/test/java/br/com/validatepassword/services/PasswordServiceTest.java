package br.com.validatepassword.services;

import br.com.validatepassword.services.exceptions.PasswordInvalidException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@SpringBootTest
public class PasswordServiceTest {

    private final String PASSWORD_VALID = "AbTp9!foa";
    private final String PASSWORD_WITH_WHITESPACE = "AbTp9!fo ";
    private final String PASSWORD_WITH_CHARACTER_REPEAT = "AbTp9!foA";

    @Autowired
    private PasswordService service;

    @Test
    public void isValid() throws Exception {
        assertTrue(service.isValid(PASSWORD_VALID));
    }

    @Test
    public void isValidWithException() {
        assertThrows(PasswordInvalidException.class, () -> service.isValid(PASSWORD_WITH_WHITESPACE));
        assertThrows(PasswordInvalidException.class, () -> service.isValid(PASSWORD_WITH_CHARACTER_REPEAT));
    }
}
