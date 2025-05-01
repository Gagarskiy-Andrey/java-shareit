package ru.practicum.shareit.exceptions;

import lombok.Value;

@Value
public class ErrorResponse {
    private final String error;
    private final String description;
}
