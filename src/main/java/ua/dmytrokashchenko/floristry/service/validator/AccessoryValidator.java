package ua.dmytrokashchenko.floristry.service.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.service.exception.AccessoryValidationException;

@Component
public class AccessoryValidator {
    private static final Logger LOGGER = Logger.getLogger(AccessoryValidator.class);

    public void validate(Accessory accessory) throws AccessoryValidationException {
        if (accessory == null) {
            LOGGER.warn("Invalid accessory");
            throw new AccessoryValidationException("Invalid accessory");
        }
        if (accessory.getName() == null) {
            LOGGER.warn("Invalid name of accessory");
            throw new AccessoryValidationException("Invalid name of accessory");
        }
        if (accessory.getColor() == null) {
            LOGGER.warn("Invalid color of accessory");
            throw new AccessoryValidationException("Invalid color of accessory");
        }
        if (accessory.getPrice() <= 0) {
            LOGGER.warn("Invalid prise of accessory");
            throw new AccessoryValidationException("Invalid prise of accessory");
        }
    }
}
