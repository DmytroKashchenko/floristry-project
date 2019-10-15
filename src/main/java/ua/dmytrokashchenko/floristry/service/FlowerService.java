package ua.dmytrokashchenko.floristry.service;

import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.service.exception.FlowerAddingException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerGettingException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.FlowerValidationException;

import java.util.List;

public interface FlowerService {
    Flower addFlower(Flower flower) throws FlowerValidationException, FlowerAddingException;
    Flower deleteFlower(Flower flower) throws FlowerRemovalException, FlowerValidationException;
    List<Flower> getAll();
    Flower getFlowerById(Long id) throws FlowerGettingException;
}
