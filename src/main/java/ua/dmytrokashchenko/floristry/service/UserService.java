package ua.dmytrokashchenko.floristry.service;

import ua.dmytrokashchenko.floristry.domain.user.User;
import ua.dmytrokashchenko.floristry.service.exception.UserLoginException;
import ua.dmytrokashchenko.floristry.service.exception.UserRegistrationException;
import ua.dmytrokashchenko.floristry.service.exception.UserValidationException;

import java.util.List;

public interface UserService {
    User register(User user) throws UserRegistrationException, UserValidationException;
    User login(String email, String password) throws UserLoginException;
    List<User> getAllUsers();
}
