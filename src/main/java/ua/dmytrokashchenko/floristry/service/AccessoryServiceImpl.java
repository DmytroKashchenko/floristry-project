package ua.dmytrokashchenko.floristry.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.repository.AccessoryRepository;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryAddingException;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryValidationException;
import ua.dmytrokashchenko.floristry.service.exception.GettingAccessoryException;
import ua.dmytrokashchenko.floristry.service.validator.AccessoryValidator;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final AccessoryValidator accessoryValidator;
    private static final Logger LOGGER = Logger.getLogger(AccessoryServiceImpl.class);

    @Autowired
    public AccessoryServiceImpl(AccessoryRepository accessoryRepository,
                                AccessoryValidator accessoryValidator) {
        this.accessoryRepository = accessoryRepository;
        this.accessoryValidator = accessoryValidator;
    }

    @Override
    public Accessory addAccessory(Accessory accessory) throws AccessoryAddingException, AccessoryValidationException {
        accessoryValidator.validate(accessory);
        if (accessoryRepository.findById(accessory.getId()).isPresent()) {
            LOGGER.warn("Accessory with this id already added");
            throw new AccessoryAddingException("Accessory with this id already added");
        }
        return accessoryRepository.save(accessory);
    }

    @Override
    public Accessory deleteAccessory(Accessory accessory) throws AccessoryRemovalException, AccessoryValidationException {
        accessoryValidator.validate(accessory);
        if (!accessoryRepository.findById(accessory.getId()).isPresent()) {
            LOGGER.warn("No accessory found to remove");
            throw new AccessoryRemovalException("No accessory found to remove");
        }
        Accessory accessoryForReturn = accessoryRepository.delete(accessory.getId()).orElse(accessory);
        if (accessoryRepository.findById(accessory.getId()).isPresent()) {
            LOGGER.warn("Accessory deletion error");
            throw new AccessoryRemovalException("Accessory deletion error");
        }
        return accessoryForReturn;
    }

    @Override
    public List<Accessory> getAll() {
        return new ArrayList<>(accessoryRepository.findAll());
    }

    @Override
    public Accessory getAccessoryById(Long id) throws GettingAccessoryException {
        if (accessoryRepository.findById(id).isPresent()) {
            return accessoryRepository.findById(id).get();
        }
        throw new GettingAccessoryException("Accessory with such id not found");
    }
}
