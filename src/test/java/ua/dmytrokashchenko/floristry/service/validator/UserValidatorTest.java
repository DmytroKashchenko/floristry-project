package ua.dmytrokashchenko.floristry.service.validator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import ua.dmytrokashchenko.floristry.domain.user.Address;
import ua.dmytrokashchenko.floristry.domain.user.User;
import ua.dmytrokashchenko.floristry.domain.user.UserRole;
import ua.dmytrokashchenko.floristry.service.exception.UserValidationException;

public class UserValidatorTest {
    private User testUser;
    private UserValidator userValidator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        userValidator = new UserValidator();
        Address testAddress = new Address("Kyiv", "Street", 14, 101);
        testUser = new User.UserBuilder()
                .withName("Username")
                .withSurname("UserSurname")
                .withEmail("email@gmail.com")
                .withPassword("qwe123")
                .withUserRole(UserRole.CLIENT)
                .withAddress(testAddress)
                .build();
    }

    @Test
    public void shouldPassValidation() throws UserValidationException {
        userValidator.validateUser(testUser);
    }

    @Test
    public void shouldThrowValidationExceptionForUserName() throws UserValidationException {
        expectedException.expect(UserValidationException.class);
        expectedException.expectMessage("Wrong username");
        User testUserSpy = Mockito.spy(testUser);
        Mockito.when(testUserSpy.getName()).thenReturn("not%5actualName");
        userValidator.validateUser(testUserSpy);
    }

    @Test
    public void shouldThrowValidationExceptionForUserEmail() throws UserValidationException {
        expectedException.expect(UserValidationException.class);
        expectedException.expectMessage("Wrong email");
        User testUserSpy = Mockito.spy(testUser);
        Mockito.when(testUserSpy.getEmail()).thenReturn("notEmail");
        userValidator.validateUser(testUserSpy);
    }

    @Test
    public void shouldThrowValidationExceptionForUserPassword() throws UserValidationException {
        expectedException.expect(UserValidationException.class);
        expectedException.expectMessage("Wrong password");
        User testUserSpy = Mockito.spy(testUser);
        Mockito.when(testUserSpy.getPassword()).thenReturn("sd");
        userValidator.validateUser(testUserSpy);
    }
}