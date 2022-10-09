package com.example.gradingsystem.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.logging.Logger;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public String convertToDatabaseColumn(Role role) {
        if(role == null)
            return null;

        String dbValue = null;
        switch (role) {
            case ADMIN:
                dbValue = "admin";
                break;
            case TEACHER:
                dbValue = "teacher";
                break;
            case STUDENT:
                dbValue = "student";
                break;
        }
        return dbValue;
    }

    @Override
    public Role convertToEntityAttribute(String s) {
        if(s == null)
            return null;

        Role role = null;
        switch (s) {
            case "admin":
                role = Role.ADMIN;
                break;
            case "teacher":
                role = Role.TEACHER;
                break;
            case "student":
                role = Role.STUDENT;
                break;
        }
        return role;
    }
}
