package ua.dmytrokashchenko.floristry.repository;

import org.springframework.stereotype.Repository;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;

import java.util.*;

@Repository
public class AccessoryRepositoryImpl implements AccessoryRepository {
    private Map<Long, Accessory> idToAccessory = new HashMap<>();

    @Override
    public Accessory save(Accessory accessory) {
        if (accessory == null) {
            return null;
        }
        return idToAccessory.put(accessory.getId(), accessory);
    }

    @Override
    public Optional<Accessory> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(idToAccessory.get(id));
    }

    @Override
    public void update(Accessory accessory) {
        if (accessory == null) {
            return;
        }
        idToAccessory.put(accessory.getId(), accessory);
    }

    @Override
    public Optional<Accessory> delete(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(idToAccessory.remove(id));
    }

    @Override
    public List<Accessory> findAll() {
        return new ArrayList<>(idToAccessory.values());
    }
}
