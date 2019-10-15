package ua.dmytrokashchenko.floristry.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.domain.bouquet.Bouquet;
import ua.dmytrokashchenko.floristry.domain.enums.StemLength;
import ua.dmytrokashchenko.floristry.repository.BouquetRepository;
import ua.dmytrokashchenko.floristry.service.exception.BouquetAddingException;
import ua.dmytrokashchenko.floristry.service.exception.BouquetRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.BouquetValidatorException;
import ua.dmytrokashchenko.floristry.service.exception.GettingBouquetException;
import ua.dmytrokashchenko.floristry.service.validator.BouquetValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BouquetServiceImpl implements BouquetService {
    private final BouquetRepository bouquetRepository;
    private final BouquetValidator bouquetValidator;
    private static final Logger LOGGER = Logger.getLogger(BouquetServiceImpl.class);

    @Autowired
    public BouquetServiceImpl(BouquetRepository bouquetRepository, BouquetValidator bouquetValidator) {
        this.bouquetRepository = bouquetRepository;
        this.bouquetValidator = bouquetValidator;
    }

    @Override
    public Bouquet addBouquet(Bouquet bouquet) throws BouquetAddingException, BouquetValidatorException {
        bouquetValidator.validateBouquet(bouquet);
        if (bouquetRepository.findById(bouquet.getId()).isPresent()) {
            LOGGER.warn("Such bouquet already exist");
            throw new BouquetAddingException("Such bouquet already exist");
        }
        return bouquetRepository.save(bouquet);
    }

    @Override
    public Bouquet deleteBouquet(Bouquet bouquet) throws BouquetRemovalException, BouquetValidatorException {
        bouquetValidator.validateBouquet(bouquet);
        if (!bouquetRepository.findById(bouquet.getId()).isPresent()) {
            LOGGER.warn("No such bouquet");
            throw new BouquetRemovalException("No such bouquet");
        }
        Bouquet bouquetForReturn = bouquetRepository.delete(bouquet.getId()).orElse(bouquet);
        if (bouquetRepository.findById(bouquet.getId()).isPresent()) {
            LOGGER.warn("Bouquet deletion error");
            throw new BouquetRemovalException("Bouquet deletion error");
        }
        return bouquetForReturn;
    }

    @Override
    public List<Bouquet> getAll() {
        return new ArrayList<>(bouquetRepository.findAll());
    }

    @Override
    public Bouquet createBouquet(String name, List<Flower> flowers, List<Accessory> accessories) {
        if (name == null || flowers == null || accessories == null) {
            LOGGER.warn("Invalid argument");
            throw new IllegalArgumentException("Invalid argument");
        }
        Bouquet bouquet = new Bouquet(name);
        for (Flower flower : flowers) {
            bouquet.addFlower(flower);
        }
        for (Accessory accessory : accessories) {
            bouquet.addAccessory(accessory);
        }
        return bouquet;
    }

    @Override
    public List<Flower> sortFlowersByFreshness(Bouquet bouquet) throws BouquetValidatorException {
        bouquetValidator.validateBouquet(bouquet);
        List<Flower> flowers = bouquet.getFlowers();
        Collections.sort(flowers);
        return flowers;
    }

    @Override
    public List<Flower> getFlowersByStemLengthRange(Bouquet bouquet, StemLength min, StemLength max)
            throws BouquetValidatorException {
        bouquetValidator.validateBouquet(bouquet);
        if (min == null || max == null) {
            LOGGER.warn("Invalid argument");
            throw new IllegalArgumentException("Invalid argument");
        }
        if (min.ordinal() > max.ordinal()) {
            LOGGER.warn("Invalid argument (min > max)");
            throw new IllegalArgumentException("Invalid argument (min > max)");
        }
        List<Flower> allFlowers = bouquet.getFlowers();
        List<Flower> flowers = new ArrayList<>();
        for (Flower flower : allFlowers) {
            if (flower.getStemLength().ordinal() >= min.ordinal()
                    && flower.getStemLength().ordinal() <= max.ordinal()) {
                flowers.add(flower);
            }
        }
        return flowers;
    }

    @Override
    public Bouquet getBouquetById(Long id) throws GettingBouquetException {
        if (bouquetRepository.findById(id).isPresent()) {
            return bouquetRepository.findById(id).get();
        }
        throw new GettingBouquetException("Bouquet with such id not found");
    }
}
