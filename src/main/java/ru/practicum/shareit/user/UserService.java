package ru.practicum.shareit.user;

import ru.practicum.shareit.user.UserDto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto dto);

    UserDto update(UserDto dto, Long id);

    void delete(Long id);

    List<UserDto> findAll();

    UserDto findById(Long id);
}
