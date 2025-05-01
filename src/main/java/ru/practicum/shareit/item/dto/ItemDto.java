package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.BookingDtoResponse;
import ru.practicum.shareit.validators.Add;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {
    Long id;
    @NotBlank(groups = {Add.class}, message = "Name can not be blank")
    String name;
    @NotBlank(groups = {Add.class}, message = "Description can not be blank")
    String description;
    @NotNull(groups = {Add.class}, message = "Available отсутствует")
    Boolean available;
    BookingDtoResponse lastBooking;

    BookingDtoResponse nextBooking;

    List<CommentDtoResponse> comments;

}
