package ua.dmytrokashchenko.floristry.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.domain.accessory.WrappingPaper;
import ua.dmytrokashchenko.floristry.domain.enums.Color;
import ua.dmytrokashchenko.floristry.repository.AccessoryRepository;
import ua.dmytrokashchenko.floristry.repository.AccessoryRepositoryImpl;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryAddingException;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryValidationException;
import ua.dmytrokashchenko.floristry.service.exception.GettingAccessoryException;
import ua.dmytrokashchenko.floristry.service.validator.AccessoryValidator;

import java.util.Optional;

import static org.junit.Assert.*;

public class AccessoryServiceImplTest {
    private AccessoryService accessoryService;
    private AccessoryValidator accessoryValidator;
    private AccessoryRepository accessoryRepository;
    private Accessory accessory;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        accessoryValidator = Mockito.mock(AccessoryValidator.class);
        accessoryRepository = Mockito.mock(AccessoryRepositoryImpl.class);
        accessory = new WrappingPaper("Blue wrapping Paper", 2500, Color.BLUE, 400, 500);
        accessoryService = new AccessoryServiceImpl(accessoryRepository, accessoryValidator);
    }

    @Test
    public void shouldSuccessfullyAddAccessory() throws AccessoryAddingException, AccessoryValidationException {
        Mockito.when(accessoryRepository.findById(accessory.getId())).thenReturn(Optional.empty());
        accessoryService.addAccessory(accessory);
    }

    @Test
    public void shouldThrowAccessoryAddingException() throws AccessoryAddingException, AccessoryValidationException {
        exception.expect(AccessoryAddingException.class);
        exception.expectMessage("Accessory with this id already added");
        Mockito.when(accessoryRepository.findById(accessory.getId())).thenReturn(Optional.of(accessory));
        accessoryService.addAccessory(accessory);
    }

    @Test
    public void shouldSuccessfullyDeleteAccessory() throws AccessoryRemovalException, AccessoryValidationException {
        Mockito.when(accessoryRepository.findById(accessory.getId()))
                .thenReturn(Optional.of(accessory))
                .thenReturn(Optional.empty());
        accessoryService.deleteAccessory(accessory);
    }

    @Test
    public void shouldThrowAccessoryRemovalExceptionWhenAccessoryNotFound()
            throws AccessoryRemovalException, AccessoryValidationException {
        exception.expect(AccessoryRemovalException.class);
        exception.expectMessage("No accessory found to remove");
        Mockito.when(accessoryRepository.findById(accessory.getId()))
                .thenReturn(Optional.empty());
        accessoryService.deleteAccessory(accessory);
    }

    @Test
    public void shouldThrowAccessoryRemovalExceptionWhenAccessoryNotRemoved()
            throws AccessoryRemovalException, AccessoryValidationException {
        exception.expect(AccessoryRemovalException.class);
        exception.expectMessage("Accessory deletion error");
        Mockito.when(accessoryRepository.findById(accessory.getId()))
                .thenReturn(Optional.of(accessory));
        accessoryService.deleteAccessory(accessory);
    }

    @Test
    public void getAccessoryByIdTest() throws GettingAccessoryException {
        Mockito.when(accessoryRepository.findById(accessory.getId())).thenReturn(Optional.of(accessory));
        Accessory expected = accessory;
        Accessory actual = accessoryService.getAccessoryById(accessory.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowGettingAccessoryExceptionWhenAccessoryNotFound() throws GettingAccessoryException {
        Mockito.when(accessoryRepository.findById(accessory.getId())).thenReturn(Optional.empty());
        exception.expect(GettingAccessoryException.class);
        exception.expectMessage("Accessory with such id not found");
        accessoryService.getAccessoryById(accessory.getId());
    }
}