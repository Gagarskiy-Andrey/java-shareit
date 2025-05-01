package ru.practicum.shareit.booking;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.practicum.shareit.booking.dto.BookingDtoRequest;
import ru.practicum.shareit.booking.dto.BookingDtoResponse;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "status", constant = "WAITING"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "item", source = "item"),
            @Mapping(target = "start", source = "dto.start"),
            @Mapping(target = "end", source = "dto.end")
    })
    Booking mapDtoToNewBooking(BookingDtoRequest dto, User user, Item item);

    @Mappings({
            @Mapping(target = "id", source = "booking.id"),
            @Mapping(target = "status", source = "booking.status"),
            @Mapping(target = "booker", source = "booking.user"),
            @Mapping(target = "item", source = "booking.item"),
            @Mapping(target = "start", source = "booking.start"),
            @Mapping(target = "end", source = "booking.end")
    })
    BookingDtoResponse mapBookingToDto(Booking booking);

    List<BookingDtoResponse> mapBookingToDtoList(List<Booking> bookings);
}
