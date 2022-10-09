package com.example.gradingsystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    ADMIN("admin"),
    TEACHER("teacher"),
    STUDENT("student");

    private final String formatted;
    private static Map<String, Role> FORMAT_MAP = Stream
            .of(Role.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    private static Map<Object, Role> Not_FORMAT_MAP = Stream
            .of(Role.values())
            .collect(Collectors.toMap(s -> s, Function.identity()));

    Role(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator
    public static Role fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }

    public static Role getRoleObject(Role role) {
        return Optional
                .ofNullable(Not_FORMAT_MAP.get(role))
                .orElseThrow(() -> new IllegalArgumentException("No roles with this name"));
    }
}
