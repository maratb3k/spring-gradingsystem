package com.example.gradingsystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StudyStatus {
    ACTIVE ("active"),
    NOTACTIVE ("notActive"),
    BREAK ("break");

    private final String formatted;
    private static Map<String, StudyStatus> FORMAT_MAP = Stream
            .of(StudyStatus.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    private static Map<Object, StudyStatus> Not_FORMAT_MAP = Stream
            .of(StudyStatus.values())
            .collect(Collectors.toMap(s -> s, Function.identity()));

    StudyStatus(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator
    public static StudyStatus fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }

    public static StudyStatus getStudyStatusObject(StudyStatus studyStatus) {
        return Optional
                .ofNullable(Not_FORMAT_MAP.get(studyStatus))
                .orElseThrow(() -> new IllegalArgumentException("No studyStatus with this name"));
    }

}
