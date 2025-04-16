package ru.practicum.shareit.user;

import ru.practicum.shareit.user.UserDto.UserDto;


import java.util.List;

public interface UserService {

    public UserDto save(UserDto user);

    public UserDto update(UserDto user, Long id);

    UserDto findById(Long id);

    public void delete(Long id);

    public List<UserDto> getAll();
}
