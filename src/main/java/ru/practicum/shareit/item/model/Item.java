package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.validators.Add;
import ru.practicum.shareit.validators.Update;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class Item {
    @NotNull(groups = {Update.class}, message = "Id отсутствует")
    private Long id;
    @NotBlank(groups = {Add.class, Update.class}, message = "Name can not be blank")
    private String name;
    @NotBlank(groups = {Add.class}, message = "Description can not be blank")
    private String description;
    @NotNull(groups = {Add.class}, message = "Available отсутствует")
    private Boolean available;
    private Long owner;
    private Long request;
}
