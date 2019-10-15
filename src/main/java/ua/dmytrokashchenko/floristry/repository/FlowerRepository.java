package ua.dmytrokashchenko.floristry.repository;

import ua.dmytrokashchenko.floristry.domain.flower.Flower;

import java.util.List;
import java.util.Optional;

public interface FlowerRepository {
    Flower save(Flower flower);

    Optional<Flower> findById(Long id);

    void update(Flower flower);

    Optional<Flower> delete(Long id);

    List<Flower> findAll();
}
