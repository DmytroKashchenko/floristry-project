package ua.dmytrokashchenko.floristry.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.domain.accessory.Cord;
import ua.dmytrokashchenko.floristry.domain.bouquet.Bouquet;
import ua.dmytrokashchenko.floristry.domain.enums.Color;
import ua.dmytrokashchenko.floristry.domain.enums.StemLength;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.domain.flower.Rose;
import ua.dmytrokashchenko.floristry.repository.BouquetRepository;
import ua.dmytrokashchenko.floristry.repository.BouquetRepositoryImpl;
import ua.dmytrokashchenko.floristry.service.exception.BouquetAddingException;
import ua.dmytrokashchenko.floristry.service.exception.BouquetRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.BouquetValidatorException;
import ua.dmytrokashchenko.floristry.service.exception.GettingBouquetException;
import ua.dmytrokashchenko.floristry.service.validator.BouquetValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class BouquetServiceImplTest {
    private BouquetService bouquetService;
    private BouquetRepository bouquetRepository;
    private BouquetValidator bouquetValidator;
    private Bouquet bouquet;
    private Flower testFlower1;
    private Flower testFlower2;
    private Accessory testAccessory;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        bouquetRepository = Mockito.mock(BouquetRepositoryImpl.class);
        bouquetValidator = Mockito.mock(BouquetValidator.class);
        bouquetService = new BouquetServiceImpl(bouquetRepository, bouquetValidator);
        bouquet = new Bouquet("Test bouquet");
        testFlower1 = new Rose.RoseBuilder().withName("Rose1").withPrice(2200).withPrickles(true).withColor(Color.RED)
                .withDateOfManufacture(LocalDate.of(2019, 10, 10)).withShelfLifeDays(10)
                .withStemLength(StemLength.MEDIUM).build();
        testFlower2 = new Rose.RoseBuilder().withName("Rose2").withPrice(4200).withPrickles(true).withColor(Color.PINK)
                .withDateOfManufacture(LocalDate.of(2019, 10, 11)).withShelfLifeDays(10)
                .withStemLength(StemLength.PREMIUM).build();
        testAccessory = new Cord("Cord", 1000, Color.RED, 200);
    }

    @Test
    public void shouldSuccessfullyAddBouquet() throws BouquetValidatorException, BouquetAddingException {
        Mockito.when(bouquetRepository.findById(bouquet.getId())).thenReturn(Optional.empty());
        bouquetService.addBouquet(bouquet);
    }

    @Test
    public void shouldThrowBouquetAddingException() throws BouquetValidatorException, BouquetAddingException {
        exception.expect(BouquetAddingException.class);
        exception.expectMessage("Such bouquet already exist");
        Mockito.when(bouquetRepository.findById(bouquet.getId())).thenReturn(Optional.of(bouquet));
        bouquetService.addBouquet(bouquet);
    }

    @Test
    public void shouldSuccessfullyDeleteBouquet() throws BouquetValidatorException, BouquetRemovalException {
        Mockito.when(bouquetRepository.findById(bouquet.getId()))
                .thenReturn(Optional.of(bouquet))
                .thenReturn(Optional.empty());
        bouquetService.deleteBouquet(bouquet);
    }

    @Test
    public void shouldThrowBouquetRemovalExceptionWhenBouquetNotFound()
            throws BouquetValidatorException, BouquetRemovalException {
        exception.expect(BouquetRemovalException.class);
        exception.expectMessage("No such bouquet");
        Mockito.when(bouquetRepository.findById(bouquet.getId())).thenReturn(Optional.empty());
        bouquetService.deleteBouquet(bouquet);
    }

    @Test
    public void shouldThrowBouquetRemovalExceptionWhenBouquetNotRemoved()
            throws BouquetValidatorException, BouquetRemovalException {
        exception.expect(BouquetRemovalException.class);
        exception.expectMessage("Bouquet deletion error");
        Mockito.when(bouquetRepository.findById(bouquet.getId())).thenReturn(Optional.of(bouquet));
        bouquetService.deleteBouquet(bouquet);
    }

    @Test
    public void createBouquetTest() {
        ArrayList<Flower> flowers = new ArrayList<>();
        ArrayList<Accessory> accessories = new ArrayList<>();
        flowers.add(testFlower1);
        flowers.add(testFlower2);
        accessories.add(testAccessory);
        String bouquetName = "Test name";
        bouquetService.createBouquet(bouquetName, flowers, accessories);
    }

    @Test
    public void getFlowersByStemLengthRange() throws BouquetValidatorException {
        List<Flower> expected = new ArrayList<>();
        expected.add(testFlower2);
        bouquet.addFlower(testFlower1);
        bouquet.addFlower(testFlower2);
        List<Flower> actual = bouquetService.getFlowersByStemLengthRange(bouquet, StemLength.PREMIUM, StemLength.WOW);
        assertEquals(expected, actual);
    }

    @Test
    public void getBouquetByIdTest() throws GettingBouquetException {
        Mockito.when(bouquetRepository.findById(bouquet.getId())).thenReturn(Optional.of(bouquet));
        bouquetService.getBouquetById(bouquet.getId());
    }

    @Test
    public void shouldThrowGettingBouquetExceptionWhenBouquetNotFound() throws GettingBouquetException {
        exception.expect(GettingBouquetException.class);
        exception.expectMessage("Bouquet with such id not found");
        Mockito.when(bouquetRepository.findById(bouquet.getId())).thenReturn(Optional.empty());
        bouquetService.getBouquetById(bouquet.getId());
    }
}
