package ua.dmytrokashchenko.floristry.service.validator;

import org.springframework.stereotype.Component;
import ua.dmytrokashchenko.floristry.domain.user.Address;

@Component
public class AddressValidator {
    public void validateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException(); // + description or personal exception + logger
        }
        if (address.getCity() == null || address.getCity().length() > 100) {
            throw new RuntimeException(); // + description or personal exception + logger
        }
        if (address.getStreet() == null || address.getStreet().length() > 100) {
            throw new RuntimeException(); // + description or personal exception + logger
        }
        if (address.getIndex() < 0) {
            throw new RuntimeException(); // + description or personal exception + logger
        }
        if (address.getBuildingNumber() < 0) {
            throw new RuntimeException(); // + description or personal exception + logger
        }
    }
}
