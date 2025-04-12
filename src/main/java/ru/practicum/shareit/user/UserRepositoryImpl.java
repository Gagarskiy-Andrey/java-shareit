package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exemptions.NotFoundException;
import ru.practicum.shareit.exemptions.ValidationException;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private Map<Long, User> users = new HashMap<>();
    private Long userId = 1L;

    @Override
    public User save(User user) {
        User newUser = user;
        emailCheck(user);
        newUser.setId(generateId());
        users.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public User update(User user) {
        User updUser = users.get(user.getId());
        if (user.getEmail() != null && !user.getEmail().equals(users.get(user.getId()).getEmail())) {
            emailCheck(user);
            updUser.setEmail(user.getEmail());
        }
        updUser.setName(user.getName());
        users.put(updUser.getId(), updUser);
        return updUser;
    }

    @Override
    public User findById(Long id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException("User с указанным id не найден");
        }
        return users.get(id);
    }

    @Override
    public void delete(Long id) {
        users.remove(id);
    }

    @Override
    public List<User> getAll() {
        return users.values().stream().toList();
    }

    private Long generateId() {
        return userId++;
    }

    private void emailCheck(User user) {
        List<User> emailCheck = users.values()
                .stream()
                .filter(userOfMap -> userOfMap.getEmail().equals(user.getEmail()))
                .toList();
        if (!emailCheck.isEmpty()) {
            throw new ValidationException("Пользователь с указанным email существует");
        }
    }
}
