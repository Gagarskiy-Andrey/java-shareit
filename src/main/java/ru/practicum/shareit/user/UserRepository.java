package ru.practicum.shareit.user;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserRepository {

    public User save(User user);

    public User update(User user);

    User findById(Long id);

    public void delete(Long id);

    public List<User> getAll();
}
