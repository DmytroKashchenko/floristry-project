package ua.dmytrokashchenko.floristry.service.validator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.domain.accessory.Cord;
import ua.dmytrokashchenko.floristry.domain.enums.Color;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryValidationException;

import static org.junit.Assert.*;

public class AccessoryValidatorTest {
    private AccessoryValidator accessoryValidator;
    private Accessory accessory;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        accessory = new Cord("Accessory", -10, Color.WHITE, 20);
        accessoryValidator = new AccessoryValidator();
    }

    @Test
    public void validateAccessoryPriceTest() throws AccessoryValidationException {
        expectedException.expect(AccessoryValidationException.class);
        expectedException.expectMessage("Invalid prise of accessory");
        accessoryValidator.validate(accessory);

    }
}