package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.ValidationException;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.UserDto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto create(UserDto dto) {
        User user = repository.save(userMapper.toUser(dto));
        return userMapper.toUserDto(user);
    }

    @Transactional
    @Override
    public UserDto update(UserDto dto, Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Обновляемый пользователь не найден"));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            user.setName(dto.getName());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            if (repository.findByEmail(dto.getEmail()).isEmpty()) {
                user.setEmail(dto.getEmail());
            } else {
                log.error("Указанный email существует - {}", dto.getEmail());
                throw new ValidationException("Указанный email существует");
            }
        }

        repository.save(user);
        return userMapper.toUserDto(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = repository.getReferenceById(id);
        repository.delete(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = repository.findAll();
        return userMapper.mapUserToDto(users);
    }

    @Override
    public UserDto findById(Long id) {
        User user = repository.findById(id).orElseThrow();
        return userMapper.toUserDto(user);
    }
}
