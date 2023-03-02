package com.example.gradingsystem.enums;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.logging.Logger;

@Converter(autoApply = true)
public class StudyStatusConverter implements AttributeConverter<StudyStatus, String> {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public String convertToDatabaseColumn(StudyStatus studyStatus) {
        if(studyStatus == null)
            return null;

        String dbValue = null;
        switch (studyStatus) {
            case ACTIVE:
                dbValue = "active";
                break;
            case NOTACTIVE:
                dbValue = "notActive";
                break;
            case BREAK:
                dbValue = "break";
                break;
        }
        return dbValue;
    }

    @Override
    public StudyStatus convertToEntityAttribute(String s) {
        if(s == null)
            return null;

        StudyStatus studyStatus = null;
        switch (s) {
            case "active":
                studyStatus = StudyStatus.ACTIVE;
                break;
            case "notActive":
                studyStatus = StudyStatus.NOTACTIVE;
                break;
            case "break":
                studyStatus = StudyStatus.BREAK;
                break;
        }
        return studyStatus;
    }
}
