package ru.practicum.shareit.booking;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import ru.practicum.shareit.booking.dto.BookItemRequestDto;
import ru.practicum.shareit.booking.dto.BookingState;


@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BookingController {
    private final BookingClient bookingClient;

    @GetMapping
    public ResponseEntity<Object> getBookings(@RequestHeader("X-Sharer-User-Id") long userId,
                                              @RequestParam(name = "state", defaultValue = "all") String stateParam,
                                              @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                              @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        BookingState state = BookingState.from(stateParam)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));
        log.info("Get booking with state {}, userId={}, from={}, size={}", stateParam, userId, from, size);
        return bookingClient.getBookings(userId, state, from, size);
    }

    @PostMapping
    public ResponseEntity<Object> bookItem(@RequestHeader("X-Sharer-User-Id") long userId,
                                           @RequestBody @Valid BookItemRequestDto requestDto) {
        log.info("Creating booking {}, userId={}", requestDto, userId);
        bookinValid(requestDto);
        return bookingClient.bookItem(userId, requestDto);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                             @PathVariable Long bookingId) {
        log.info("Get booking {}, userId={}", bookingId, userId);
        return bookingClient.getBooking(userId, bookingId);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> confirmBooking(
            @RequestHeader("X-Sharer-User-Id") @NotNull Long userId,
            @PathVariable Long bookingId,
            @RequestParam("approved") @NotNull Boolean approved) {
        log.info("Подтверждение/Отклонение бронирования: bookingId={}, approved={}", bookingId, approved);
        return bookingClient.confirmBooking(userId, bookingId, approved);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllBookingsForOwner(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(value = "state", defaultValue = "ALL") String state) {
        log.info("Получение списка бронирований для владельца: userId={}, state={}", userId, state);
        BookingState bookingState = BookingState.from(state)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + state));
        return bookingClient.getAllBookingsForOwner(userId, bookingState);
    }

    private void bookinValid(BookItemRequestDto dto) {
        if (dto.getStart().isAfter(dto.getEnd()) || dto.getStart().isEqual(dto.getEnd())) {
            throw new ValidationException("Дата окончания бронирования не может быть раньше начала или равна ему");
        }
    }
}