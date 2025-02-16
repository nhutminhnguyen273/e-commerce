package com.example.user_service.enums;

import com.example.shared_library.exception.custom.AlreadyExistedException;
import com.example.shared_library.exception.custom.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Gender {
    MALE("Male"),
    FEMALE("Female");
    private final String label;

    @JsonValue
    public String getLabel() {
        return this.label;
    }

    @JsonCreator
    public static Gender fromLabel(String label) {
        return Arrays.stream(Gender.values())
                .filter(g -> g.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException(label + " not found")
                );
    }
}
