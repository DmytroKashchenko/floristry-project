package ua.dmytrokashchenko.floristry.service;

import org.springframework.stereotype.Component;

import static ua.dmytrokashchenko.floristry.sourcecode.BCrypt.*;

@Component
public class PasswordServiceImpl implements PasswordService {
    @Override
    public String encode(String password) {
        if (password == null) {
            throw new IllegalArgumentException();
        }
        return hashpw(password, gensalt(12));

    }

    @Override
    public boolean matchPassword(String originalPassword, String encryptedPassword) {
        if (originalPassword == null || encryptedPassword == null) {
            return false;
        }
        return checkpw(originalPassword, encryptedPassword);
    }
}
