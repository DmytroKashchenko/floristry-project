package ua.dmytrokashchenko.floristry.repository;

import org.springframework.stereotype.Repository;
import ua.dmytrokashchenko.floristry.domain.user.User;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> emailToUser = new HashMap<>();

    @Override
    public User save(User user) {
        if (user == null) {
            return null;
        }
        return emailToUser.put(user.getEmail(), user);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            return;
        }
        emailToUser.put(user.getEmail(), user);
    }

    @Override
    public Optional<User> delete(String email) {
        if (email == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(emailToUser.remove(email));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(emailToUser.values());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(emailToUser.get(email));
    }
}
