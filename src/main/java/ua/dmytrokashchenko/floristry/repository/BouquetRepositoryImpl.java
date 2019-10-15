package ua.dmytrokashchenko.floristry.repository;

import org.springframework.stereotype.Repository;
import ua.dmytrokashchenko.floristry.domain.bouquet.Bouquet;

import java.util.*;

@Repository
public class BouquetRepositoryImpl implements BouquetRepository {
    private Map<Long, Bouquet> idToBouquet = new HashMap<>();

    @Override
    public Bouquet save(Bouquet flower) {
        if (flower == null) {
            return null;
        }
        return idToBouquet.put(flower.getId(), flower);
    }

    @Override
    public Optional<Bouquet> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(idToBouquet.get(id));
    }

    @Override
    public void update(Bouquet flower) {
        if (flower == null) {
            return;
        }
        idToBouquet.put(flower.getId(), flower);
    }

    @Override
    public Optional<Bouquet> delete(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(idToBouquet.remove(id));
    }

    @Override
    public List<Bouquet> findAll() {
        return new ArrayList<>(idToBouquet.values());
    }
}
