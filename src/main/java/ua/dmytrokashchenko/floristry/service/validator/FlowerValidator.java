package ua.dmytrokashchenko.floristry.service.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.service.exception.FlowerValidationException;

@Component
public class FlowerValidator {
    private static final Logger LOGGER = Logger.getLogger(FlowerValidator.class);

    public void validateFlower(Flower flower) throws FlowerValidationException {
        if (flower == null) {
            LOGGER.warn("Invalid flower");
            throw new FlowerValidationException("Invalid flower");
        }
        if (flower.getName() == null) {
            LOGGER.warn("Invalid name of flower");
            throw new FlowerValidationException("Invalid name of flower");
        }
        if (flower.getStemLength() == null) {
            LOGGER.warn("Invalid flower stem length");
            throw new FlowerValidationException("Invalid flower stem length");
        }
        if (flower.getColor() == null) {
            LOGGER.warn("Invalid flower color");
            throw new FlowerValidationException("Invalid flower color");
        }
        if (flower.getDateOfManufacture() == null) {
            LOGGER.warn("Invalid flower date of manufacture");
            throw new FlowerValidationException("Invalid flower date of manufacture");
        }
        if (flower.getPrice() <= 0) {
            LOGGER.warn("Invalid flower price");
            throw new FlowerValidationException("Invalid flower price");
        }
        if (flower.getShelfLifeDays() <= 0) {
            LOGGER.warn("Invalid flower shelf life");
            throw new FlowerValidationException("Invalid flower shelf life");
        }
    }
}
