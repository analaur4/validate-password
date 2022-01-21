package br.com.validatepassword.services;

import br.com.validatepassword.services.exceptions.PasswordInvalidException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PasswordService {

    public boolean isValid(String password) throws Exception {
        try {
            for(String pass : password.split("")) {
                if(pass.contains(" ")) throw new PasswordInvalidException("A senha não deve conter espaços em branco.");
                if(StringUtils.countOccurrencesOf(password, pass) > 1) throw new PasswordInvalidException("A senha não deve conter caracteres repetidos");
            }

            return true;

        } catch (PasswordInvalidException e) {
            throw new PasswordInvalidException(e.getMessage());

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
