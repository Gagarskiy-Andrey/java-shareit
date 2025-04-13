package ru.practicum.shareit.user.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.validators.Add;
import ru.practicum.shareit.validators.Update;

@Data
@EqualsAndHashCode()
public class UserDto {
    private Long id;
    @NotBlank(groups = {Add.class}, message = "Name can not be blank")
    private String name;
    @NotBlank(groups = {Add.class}, message = "Email can not be blank")
    @Email(groups = {Add.class, Update.class}, message = "Email uncorrected")
    private String email;
}
