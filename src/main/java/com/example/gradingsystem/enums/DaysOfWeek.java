package com.example.gradingsystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DaysOfWeek {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");


    private final String formatted;
    private static Map<String, DaysOfWeek> FORMAT_MAP = Stream
            .of(DaysOfWeek.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    private static Map<Object, DaysOfWeek> Not_FORMAT_MAP = Stream
            .of(DaysOfWeek.values())
            .collect(Collectors.toMap(s -> s, Function.identity()));

    DaysOfWeek(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator // This is the factory method and must be static
    public static DaysOfWeek fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }

    public static DaysOfWeek getDayObject(DaysOfWeek daysOfWeek) {
        return Optional
                .ofNullable(Not_FORMAT_MAP.get(daysOfWeek))
                .orElseThrow(() -> new IllegalArgumentException("No day with this name"));
    }
}
