package ua.dmytrokashchenko.floristry.service;

import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryAddingException;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryRemovalException;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryValidationException;
import ua.dmytrokashchenko.floristry.service.exception.GettingAccessoryException;

import java.util.List;

public interface AccessoryService {
    Accessory addAccessory(Accessory accessory) throws AccessoryAddingException, AccessoryValidationException;
    Accessory deleteAccessory(Accessory accessory) throws AccessoryRemovalException, AccessoryValidationException;
    List<Accessory> getAll();
    Accessory getAccessoryById(Long id) throws GettingAccessoryException;
}
