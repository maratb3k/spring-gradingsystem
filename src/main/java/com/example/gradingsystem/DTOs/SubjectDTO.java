package com.example.gradingsystem.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDTO {
    private int id;
    private String name;
    private List<GroupDTO> groups;
}
