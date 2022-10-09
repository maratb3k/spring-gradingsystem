package com.example.gradingsystem.entities;
import com.example.gradingsystem.DTOs.AdminDTO;
import com.example.gradingsystem.DTOs.StudentDTO;
import com.example.gradingsystem.enums.Role;
import com.example.gradingsystem.enums.RoleConverter;
import com.example.gradingsystem.exceptions.NullRobucksException;
import com.example.gradingsystem.exceptions.RobucksExceededException;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = Teacher.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
    @Convert(converter = RoleConverter.class)
    private Role role;
    @Column(name = "robucks")
    private int robucks;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group groupID;


    public void addRobucks(int robucksToAdd) {
        if(this.getRobucks() == 2000){
            throw new RobucksExceededException("The student has a sufficient amount of robucks");
        }
        else {
            if(this.getRobucks() + robucksToAdd <= 2000) {
                int totalRobucks = this.getRobucks() + robucksToAdd;
                this.setRobucks(totalRobucks);
            }
            else {
                int availableAmountToAdd = 2000 - this.getRobucks();
                throw new RobucksExceededException("You can add only " + availableAmountToAdd + " robucks.");
            }
        }
    }

    public void substractRobucks(int robucksToSubstract) {
        if(this.getRobucks() == 0){
            throw new NullRobucksException("The student has no robucks");
        }
        else {
            if(this.getRobucks() - robucksToSubstract >= 0) {
                int totalRobucks = this.getRobucks() - robucksToSubstract;
                this.setRobucks(totalRobucks);
            }
            else {
                throw new RobucksExceededException("You can substract only " + this.getRobucks() + " robucks.");
            }
        }
    }

    public void resetRobucks() {
        this.setRobucks(0);
    }

    public StudentDTO toDto() {
        return new StudentDTO(
                this.id,
                this.name,
                this.surname,
                user,
                this.role,
                this.robucks,
                groupID
        );
    }
}
