package ua.dmytrokashchenko.floristry.repository;

import ua.dmytrokashchenko.floristry.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    void update(User user);

    Optional<User> delete(String email);

    List<User> findAll();

    Optional<User> findByEmail(String email);
}
