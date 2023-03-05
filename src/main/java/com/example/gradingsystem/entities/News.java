package com.example.gradingsystem.entities;

import com.example.gradingsystem.DTOs.NewsDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = News.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;

    @Column(name = "posted")
    private LocalDate posted;

    public NewsDTO toDto() {
        return new NewsDTO(
                this.id,
                this.name,
                this.description,
                this.photo,
                this.posted
        );
    }
}
