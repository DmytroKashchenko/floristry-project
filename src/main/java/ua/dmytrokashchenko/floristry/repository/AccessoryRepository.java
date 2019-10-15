package ua.dmytrokashchenko.floristry.repository;

import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;

import java.util.List;
import java.util.Optional;

public interface AccessoryRepository {
    Accessory save(Accessory accessory);

    Optional<Accessory> findById(Long id);

    void update(Accessory accessory);

    Optional<Accessory> delete(Long id);

    List<Accessory> findAll();
}
