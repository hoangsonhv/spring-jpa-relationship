package com.example.demospring.manytomany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "students")
public class Student {
    @Id
    private String rollNumber;
    private String fullName;
    private String gender;
    private int status;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentGrade> studentGrades;

}
