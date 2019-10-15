package ua.dmytrokashchenko.floristry.service.validator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.domain.flower.Rose;
import ua.dmytrokashchenko.floristry.domain.enums.Color;
import ua.dmytrokashchenko.floristry.domain.enums.StemLength;
import ua.dmytrokashchenko.floristry.service.exception.FlowerValidationException;

import java.time.LocalDate;

public class FlowerValidatorTest {
    private FlowerValidator flowerValidator;
    private Flower testFlower;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        testFlower = new Rose.RoseBuilder()
                .withName("TestRose")
                .withPrice(2500)
                .withPrickles(true)
                .withDateOfManufacture(LocalDate.of(2019, 5, 6))
                .withShelfLifeDays(10)
                .withColor(Color.RED)
                .withStemLength(StemLength.MEDIUM)
                .build();
        flowerValidator = new FlowerValidator();
    }

    @Test
    public void shouldPassValidationFlower() throws FlowerValidationException {
        flowerValidator.validateFlower(testFlower);
    }

    @Test
    public void shouldThrowFlowerValidationExceptionWhenCheckingPrice() throws FlowerValidationException {
        exception.expect(FlowerValidationException.class);
        exception.expectMessage("Invalid flower price");
        Flower flowerSpy = Mockito.spy(testFlower);
        Mockito.when(flowerSpy.getPrice()).thenReturn(-1);
        flowerValidator.validateFlower(flowerSpy);
    }

    @Test
    public void shouldThrowFlowerValidationExceptionWhenCheckingShelfLife() throws FlowerValidationException {
        exception.expect(FlowerValidationException.class);
        exception.expectMessage("Invalid flower shelf life");
        Flower flowerSpy = Mockito.spy(testFlower);
        Mockito.when(flowerSpy.getShelfLifeDays()).thenReturn(-1);
        flowerValidator.validateFlower(flowerSpy);
    }
}