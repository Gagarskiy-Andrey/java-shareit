package ru.practicum.shareit.user;

import org.mapstruct.Mapper;
import ru.practicum.shareit.user.UserDto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    User toUser(UserDto userDto, Long id);

    default List<UserDto> mapUserToDto(Iterable<User> users) {
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(toUserDto(user));
        }
        return result;
    }

}
