package ua.dmytrokashchenko.floristry.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.repository.FlowerRepository;
import ua.dmytrokashchenko.floristry.service.exception.FlowerAddingException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerGettingException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerValidationException;
import ua.dmytrokashchenko.floristry.service.validator.FlowerValidator;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;
    private final FlowerValidator flowerValidator;
    private static final Logger LOGGER = Logger.getLogger(FlowerServiceImpl.class);

    @Autowired
    public FlowerServiceImpl(FlowerRepository flowerRepository, FlowerValidator flowerValidator) {
        this.flowerRepository = flowerRepository;
        this.flowerValidator = flowerValidator;
    }

    @Override
    public Flower addFlower(Flower flower) throws FlowerValidationException, FlowerAddingException {
        flowerValidator.validateFlower(flower);
        if (flowerRepository.findById(flower.getId()).isPresent()) {
            LOGGER.warn("Such flower already exist");
            throw new FlowerAddingException("Such flower already exist");
        }
        return flowerRepository.save(flower);
    }

    @Override
    public Flower deleteFlower(Flower flower) throws FlowerRemovalException, FlowerValidationException {
        flowerValidator.validateFlower(flower);
        if (!flowerRepository.findById(flower.getId()).isPresent()) {
            LOGGER.warn("No flowers found to remove");
            throw new FlowerRemovalException("No flowers found to remove");
        }
        Flower flowerForReturn = flowerRepository.delete(flower.getId()).orElse(flower);
        if (flowerRepository.findById(flower.getId()).isPresent()) {
            LOGGER.warn("Flower deletion error");
            throw new FlowerRemovalException("Flower deletion error");
        }
        return flowerForReturn;
    }

    @Override
    public List<Flower> getAll() {
        return new ArrayList<>(flowerRepository.findAll());
    }

    @Override
    public Flower getFlowerById(Long id) throws FlowerGettingException {
        if (flowerRepository.findById(id).isPresent()) {
            return flowerRepository.findById(id).get();
        }
        throw new FlowerGettingException("Flower with such id not found");
    }
}
