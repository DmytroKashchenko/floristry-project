package ua.dmytrokashchenko.floristry.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dmytrokashchenko.floristry.domain.user.User;
import ua.dmytrokashchenko.floristry.domain.user.UserRole;
import ua.dmytrokashchenko.floristry.repository.UserRepository;
import ua.dmytrokashchenko.floristry.service.exception.UserLoginException;
import ua.dmytrokashchenko.floristry.service.exception.UserRegistrationException;
import ua.dmytrokashchenko.floristry.service.exception.UserValidationException;
import ua.dmytrokashchenko.floristry.service.validator.UserValidator;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final UserValidator userValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserValidator userValidator, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordService = passwordService;
    }

    @Override
    public User register(User user) throws UserRegistrationException, UserValidationException {
        userValidator.validateUser(user);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("Such email address already exists");
            throw new UserRegistrationException("Such email address already exists");
        }
        User userForSave = new User.UserBuilder()
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withAddress(user.getAddress())
                .withUserRole(UserRole.CLIENT)
                .withPassword(passwordService.encode(user.getPassword()))
                .build();
        LOGGER.info("User " + userForSave.getEmail() + " saved to repository");
        return userRepository.save(userForSave);
    }

    @Override
    public User login(String email, String password) throws UserLoginException {
        if (email == null || password == null) {
            throw new IllegalArgumentException();
        }
        User user = null;
        if (userRepository.findByEmail(email).isPresent()){
            user = userRepository.findByEmail(email).get();
        } else {
            LOGGER.warn("No such user with this login and password");
            throw new UserLoginException("No such user with this login and password");
        }
        if (!passwordService.matchPassword(password, user.getPassword())) {
            LOGGER.warn("No such user with this login and password");
            throw new UserLoginException("No such user with this login and password");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
