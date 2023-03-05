package com.example.gradingsystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsDTO {
    private int id;
    private String name;
    private String description;
    private String photo;
    private LocalDate posted;
}
