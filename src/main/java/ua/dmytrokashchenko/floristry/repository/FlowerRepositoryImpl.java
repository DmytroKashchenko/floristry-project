package ua.dmytrokashchenko.floristry.repository;

import org.springframework.stereotype.Repository;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;

import java.util.*;

@Repository
public class FlowerRepositoryImpl implements FlowerRepository {
    private Map<Long, Flower> idToFlowers = new HashMap<>();

    @Override
    public Flower save(Flower flower) {
        if (flower == null) {
            return null;
        }
        return idToFlowers.put(flower.getId(), flower);
    }

    @Override
    public Optional<Flower> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(idToFlowers.get(id));
    }

    @Override
    public void update(Flower flower) {
        if (flower == null) {
            return;
        }
        idToFlowers.put(flower.getId(), flower);
    }

    @Override
    public Optional<Flower> delete(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(idToFlowers.remove(id));
    }

    @Override
    public List<Flower> findAll() {
        return new ArrayList<>(idToFlowers.values());
    }
}
