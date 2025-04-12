package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.validators.Add;
import ru.practicum.shareit.validators.Update;

/**
 * TODO Sprint add-controllers.
 */
@Data
@EqualsAndHashCode()
public class User {
    @NotNull(groups = {Update.class}, message = "Id is empty")
    private Long id;
    @NotBlank(groups = {Add.class}, message = "Name can not be blank")
    private String name;
    @NotBlank(groups = {Add.class}, message = "Email can not be blank")
    @Email(groups = {Add.class, Update.class}, message = "Email uncorrected")
    private String email;
}
