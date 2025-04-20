package ru.practicum.shareit.user;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.user.UserDto.UserDto;

import java.util.List;

public interface UserService {
    @Transactional
    UserDto create(UserDto dto);

    @Transactional
    UserDto update(UserDto dto, Long id);

    @Transactional
    void delete(Long id);

    List<UserDto> findAll();

    UserDto findById(Long id);
}
