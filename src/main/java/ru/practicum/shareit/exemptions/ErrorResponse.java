package ru.practicum.shareit.exemptions;

import lombok.Value;

@Value
public class ErrorResponse {
    private final String error;
    private final String description;
}
