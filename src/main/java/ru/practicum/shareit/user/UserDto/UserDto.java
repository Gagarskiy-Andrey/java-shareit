package ru.practicum.shareit.user.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.validators.Add;
import ru.practicum.shareit.validators.Update;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    @NotBlank(groups = {Add.class}, message = "Name can not be blank")
    String name;
    @NotBlank(groups = {Add.class}, message = "Email can not be blank")
    @Email(groups = {Add.class, Update.class}, message = "Email uncorrected")
    String email;
}
