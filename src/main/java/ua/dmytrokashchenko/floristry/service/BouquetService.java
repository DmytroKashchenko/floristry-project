package ua.dmytrokashchenko.floristry.service;

import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.domain.bouquet.Bouquet;
import ua.dmytrokashchenko.floristry.domain.enums.StemLength;
import ua.dmytrokashchenko.floristry.service.exception.BouquetAddingException;
import ua.dmytrokashchenko.floristry.service.exception.BouquetRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.BouquetValidatorException;
import ua.dmytrokashchenko.floristry.service.exception.GettingBouquetException;

import java.util.List;

public interface BouquetService {
    Bouquet addBouquet(Bouquet bouquet) throws BouquetAddingException, BouquetValidatorException;
    Bouquet deleteBouquet(Bouquet bouquet) throws BouquetRemovalException, BouquetValidatorException;
    List<Bouquet> getAll();
    Bouquet createBouquet(String name, List<Flower> flowers, List<Accessory> accessories);
    List<Flower> sortFlowersByFreshness(Bouquet bouquet) throws BouquetValidatorException;
    List<Flower> getFlowersByStemLengthRange(Bouquet bouquet, StemLength min, StemLength max) throws BouquetValidatorException;
    Bouquet getBouquetById(Long id) throws GettingBouquetException;
}
