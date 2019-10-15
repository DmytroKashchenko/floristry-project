package ua.dmytrokashchenko.floristry.service;

public interface PasswordService {
    public String encode(String password);
    public boolean matchPassword(String originalPassword, String encryptedPassword);
}
