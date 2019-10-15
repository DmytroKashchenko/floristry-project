package ua.dmytrokashchenko.floristry.service.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ua.dmytrokashchenko.floristry.domain.user.Address;
import ua.dmytrokashchenko.floristry.domain.user.User;
import ua.dmytrokashchenko.floristry.domain.user.UserRole;
import ua.dmytrokashchenko.floristry.service.exception.UserValidationException;

import java.util.regex.Pattern;

@Component
public class UserValidator {
    private static final Logger LOGGER = Logger.getLogger(UserValidator.class);
    private static final String TEMPLATE_FOR_NAME = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"; // "[A-Z][a-zA-Z][^#&<>\\\"~;$^%{}?]{2,50}$"
    private static final String TEMPLATE_FOR_SURNAME = "[A-Z][a-zA-Z][^#&<>\\\"~;$^%{}?]{2,50}$";
    private static final String TEMPLATE_FOR_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    private static final String TEMPLATE_FOR_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
    private static final Pattern PATTERN_FOR_NAME = Pattern.compile(TEMPLATE_FOR_NAME);
    private static final Pattern PATTERN_FOR_SURNAME = Pattern.compile(TEMPLATE_FOR_SURNAME);
    private static final Pattern PATTERN_FOR_EMAIL = Pattern.compile(TEMPLATE_FOR_EMAIL);
    private static final Pattern PATTERN_FOR_PASSWORD = Pattern.compile(TEMPLATE_FOR_PASSWORD);
    private static final AddressValidator addressValidator = new AddressValidator();

    public void validateUser(User user) throws UserValidationException {
        if (user == null) {
            LOGGER.warn("Invalid user");
            throw new UserValidationException("Invalid user");
        }
        nameValidator(user.getName());
        surnameValidator(user.getSurname());
        emailValidator(user.getEmail());
        passwordValidator(user.getPassword());
        userRoleValidator(user.getUserRole());
        userAddressValidator(user.getAddress());
    }

    private void nameValidator(String name) throws UserValidationException {
        if (name == null) {
            LOGGER.warn("Invalid username");
            throw new UserValidationException("Invalid username");
        }
        if (!PATTERN_FOR_NAME.matcher(name).find()) {
            LOGGER.warn("Wrong username");
            throw new UserValidationException("Wrong username");
        }
    }

    private void surnameValidator(String surname) throws UserValidationException {
        if (surname == null) {
            LOGGER.warn("Invalid users surname");
            throw new UserValidationException("Invalid users surname");
        }
        if (!PATTERN_FOR_SURNAME.matcher(surname).find()) {
            LOGGER.warn("Wrong users surname");
            throw new UserValidationException("Wrong users surname");
        }
    }

    private void emailValidator(String email) throws UserValidationException {
        if (email == null) {
            LOGGER.warn("Invalid email");
            throw new UserValidationException("Invalid email");
        }
        if (!PATTERN_FOR_EMAIL.matcher(email).find()) {
            LOGGER.warn("Wrong email");
            throw new UserValidationException("Wrong email");
        }
    }

    private void passwordValidator(String password) throws UserValidationException {
        if (password == null) {
            LOGGER.warn("Invalid password");
            throw new UserValidationException("Invalid password");
        }
        if (!PATTERN_FOR_PASSWORD.matcher(password).find()) {
            LOGGER.warn("Wrong password");
            throw new UserValidationException("Wrong password");
        }
    }

    private void userRoleValidator(UserRole userRole) throws UserValidationException {
        if (userRole == null) {
            LOGGER.warn("Invalid users role");
            throw new UserValidationException("Invalid users role");
        }
    }

    private void userAddressValidator(Address address) throws UserValidationException {
        try {
            addressValidator.validateAddress(address);
        } catch (Exception e) {
            LOGGER.warn("Invalid users address");
            throw new UserValidationException("Invalid users address");
        }
    }
}
