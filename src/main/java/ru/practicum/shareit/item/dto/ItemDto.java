package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.practicum.shareit.validators.Add;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDto {
    private Long id;
    @NotBlank(groups = {Add.class}, message = "Name can not be blank")
    private String name;
    @NotBlank(groups = {Add.class}, message = "Description can not be blank")
    private String description;
    @NotNull(groups = {Add.class}, message = "Available отсутствует")
    private Boolean available;
    private Long owner;
    private Long request;
}
