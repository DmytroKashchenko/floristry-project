package ua.dmytrokashchenko.floristry.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import ua.dmytrokashchenko.floristry.domain.enums.Color;
import ua.dmytrokashchenko.floristry.domain.enums.StemLength;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.domain.flower.Rose;
import ua.dmytrokashchenko.floristry.repository.FlowerRepository;
import ua.dmytrokashchenko.floristry.repository.FlowerRepositoryImpl;
import ua.dmytrokashchenko.floristry.service.exception.FlowerAddingException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerGettingException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerValidationException;
import ua.dmytrokashchenko.floristry.service.validator.FlowerValidator;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class FlowerServiceImplTest {
    private FlowerService flowerService;
    private FlowerRepository flowerRepository;
    private FlowerValidator flowerValidator;
    private Flower testFlower;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        flowerRepository = Mockito.mock(FlowerRepositoryImpl.class);
        flowerValidator = Mockito.mock(FlowerValidator.class);
        flowerService = new FlowerServiceImpl(flowerRepository, flowerValidator);
        testFlower = new Rose.RoseBuilder()
                .withName("Rose1")
                .withPrice(2200)
                .withPrickles(true)
                .withColor(Color.RED)
                .withDateOfManufacture(LocalDate.of(2019, 10, 10))
                .withShelfLifeDays(10)
                .withStemLength(StemLength.WOW)
                .build();
    }

    @Test
    public void shouldSuccessfullyAddFlower() throws FlowerValidationException, FlowerAddingException {
        Mockito.when(flowerRepository.findById(testFlower.getId())).thenReturn(Optional.empty());
        flowerService.addFlower(testFlower);
    }

    @Test
    public void shouldThrowFlowerAddingException() throws FlowerValidationException, FlowerAddingException {
        exception.expect(FlowerAddingException.class);
        exception.expectMessage("Such flower already exist");
        Mockito.when(flowerRepository.findById(testFlower.getId())).thenReturn(Optional.of(testFlower));
        flowerService.addFlower(testFlower);
    }

    @Test
    public void shouldSuccessfullyDeleteFlower() throws FlowerRemovalException, FlowerValidationException {
        Mockito.when(flowerRepository.findById(testFlower.getId()))
                .thenReturn(Optional.of(testFlower))
                .thenReturn(Optional.empty());
        flowerService.deleteFlower(testFlower);
    }

    @Test
    public void shouldThrowFlowerRemovalExceptionWhenFlowerNotFound()
            throws FlowerRemovalException, FlowerValidationException {
        exception.expect(FlowerRemovalException.class);
        exception.expectMessage("No flowers found to remove");
        Mockito.when(flowerRepository.findById(testFlower.getId())).thenReturn(Optional.empty());
        flowerService.deleteFlower(testFlower);
    }

    @Test
    public void shouldThrowFlowerRemovalExceptionWhenFlowerNotRemoved()
            throws FlowerRemovalException, FlowerValidationException {
        exception.expect(FlowerRemovalException.class);
        exception.expectMessage("Flower deletion error");
        Mockito.when(flowerRepository.findById(testFlower.getId())).thenReturn(Optional.of(testFlower));
        flowerService.deleteFlower(testFlower);
    }

    @Test
    public void getFlowerByIdTest() throws FlowerGettingException {
        Mockito.when(flowerRepository.findById(testFlower.getId())).thenReturn(Optional.of(testFlower));
        Flower actual = flowerService.getFlowerById(testFlower.getId());
        Flower expected = testFlower;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowFlowerGettingExceptionWhenFlowerNotFound() throws FlowerGettingException {
        exception.expect(FlowerGettingException.class);
        exception.expectMessage("Flower with such id not found");
        Mockito.when(flowerRepository.findById(testFlower.getId())).thenReturn(Optional.empty());
        flowerService.getFlowerById(testFlower.getId());
    }
}
