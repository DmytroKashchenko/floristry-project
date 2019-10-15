package ua.dmytrokashchenko.floristry.repository;

import ua.dmytrokashchenko.floristry.domain.bouquet.Bouquet;

import java.util.List;
import java.util.Optional;

public interface BouquetRepository {
    Bouquet save(Bouquet flower);

    Optional<Bouquet> findById(Long id);

    void update(Bouquet flower);

    Optional<Bouquet> delete(Long id);

    List<Bouquet> findAll();
}
