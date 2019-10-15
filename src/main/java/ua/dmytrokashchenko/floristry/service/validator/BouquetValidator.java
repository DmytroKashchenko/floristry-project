package ua.dmytrokashchenko.floristry.service.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ua.dmytrokashchenko.floristry.domain.bouquet.Bouquet;
import ua.dmytrokashchenko.floristry.service.exception.BouquetValidatorException;

@Component
public class BouquetValidator {
    private static final Logger LOGGER = Logger.getLogger(BouquetValidator.class);

    public void validateBouquet(Bouquet bouquet) throws BouquetValidatorException {
        if (bouquet == null) {
            LOGGER.warn("Invalid bouquet");
            throw new BouquetValidatorException("Invalid bouquet");
        }
        if (bouquet.getName() == null) {
            LOGGER.warn("Invalid bouquet name");
            throw new BouquetValidatorException("Invalid bouquet name");
        }
    }
}
