package ua.dmytrokashchenko.floristry.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import ua.dmytrokashchenko.floristry.domain.user.Address;
import ua.dmytrokashchenko.floristry.domain.user.User;
import ua.dmytrokashchenko.floristry.domain.user.UserRole;
import ua.dmytrokashchenko.floristry.repository.UserRepository;
import ua.dmytrokashchenko.floristry.repository.UserRepositoryImpl;
import ua.dmytrokashchenko.floristry.service.exception.UserLoginException;
import ua.dmytrokashchenko.floristry.service.exception.UserRegistrationException;
import ua.dmytrokashchenko.floristry.service.exception.UserValidationException;
import ua.dmytrokashchenko.floristry.service.validator.UserValidator;

import java.util.Optional;

public class UserServiceImplTest {
    private UserServiceImpl userService;
    private UserRepository userRepository;
    private PasswordService passwordService;
    private UserValidator userValidator;
    private User testUser;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        passwordService = Mockito.mock(PasswordServiceImpl.class);
        userValidator = Mockito.mock(UserValidator.class);
        userRepository = Mockito.mock(UserRepositoryImpl.class);
        userService = new UserServiceImpl(userRepository, userValidator, passwordService);
        Address testAddress = new Address("Kyiv", "Street", 14, 101);
        testUser = new User.UserBuilder()
                .withName("Username")
                .withSurname("UserSurname")
                .withEmail("test@email.com")
                .withPassword("testPassword")
                .withUserRole(UserRole.CLIENT)
                .withAddress(testAddress)
                .build();
    }

    @Test
    public void shouldPassRegistration() throws UserValidationException, UserRegistrationException {
        Mockito.when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        userService.register(testUser);
    }

    @Test
    public void shouldThrowUserRegistrationException() throws UserValidationException, UserRegistrationException {
        exception.expect(UserRegistrationException.class);
        exception.expectMessage("Such email address already exists");
        Mockito.when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));
        userService.register(testUser);
    }

    @Test
    public void shouldPassLogin() throws UserLoginException {
        Mockito.when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));
        Mockito.when(passwordService.matchPassword("testPassword", testUser.getPassword()))
                .thenReturn(true);
        userService.login("test@email.com", "testPassword");
    }

    @Test
    public void shouldThrowUserLoginExceptionWhenCheckingEmail() throws UserLoginException {
        exception.expect(UserLoginException.class);
        exception.expectMessage("No such user with this login and password");
        Mockito.when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        Mockito.when(passwordService.matchPassword("testPassword", testUser.getPassword()))
                .thenReturn(true);
        userService.login("test@email.com", "testPassword");
    }

    @Test
    public void shouldThrowUserLoginExceptionWhenCheckingPassword() throws UserLoginException {
        exception.expect(UserLoginException.class);
        exception.expectMessage("No such user with this login and password");
        Mockito.when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));
        Mockito.when(passwordService.matchPassword("testPassword", testUser.getPassword()))
                .thenReturn(false);
        userService.login("test@email.com", "testPassword");
    }
}