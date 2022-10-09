package com.example.gradingsystem.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.logging.Logger;

@Converter(autoApply = true)
public class DayConverter implements AttributeConverter<DaysOfWeek, String> {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public String convertToDatabaseColumn(DaysOfWeek daysOfWeek) {
        if(daysOfWeek == null)
            return null;

        String dbValue = null;
        switch (daysOfWeek) {
            case MONDAY:
                dbValue = "monday";
                break;
            case TUESDAY:
                dbValue = "tuesday";
                break;
            case WEDNESDAY:
                dbValue = "wednesday";
                break;
            case THURSDAY:
                dbValue = "thursday";
                break;
            case FRIDAY:
                dbValue = "friday";
                break;
            case SATURDAY:
                dbValue = "saturday";
                break;
            case SUNDAY:
                dbValue = "sunday";
                break;
        }
        return dbValue;
    }

    @Override
    public DaysOfWeek convertToEntityAttribute(String s) {
        if(s == null)
            return null;

        DaysOfWeek daysOfWeek = null;
        switch (s) {
            case "monday":
                daysOfWeek = DaysOfWeek.MONDAY;
                break;
            case "tuesday":
                daysOfWeek = DaysOfWeek.TUESDAY;
                break;
            case "wednesday":
                daysOfWeek = DaysOfWeek.WEDNESDAY;
                break;
            case "thursday":
                daysOfWeek = DaysOfWeek.THURSDAY;
                break;
            case "friday":
                daysOfWeek = DaysOfWeek.FRIDAY;
                break;
            case "saturday":
                daysOfWeek = DaysOfWeek.SATURDAY;
                break;
            case "sunday":
                daysOfWeek = DaysOfWeek.SUNDAY;
                break;
        }
        return daysOfWeek;
    }
}