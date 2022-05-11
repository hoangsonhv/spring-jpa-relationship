package com.example.demospring.manytomany.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "students_grades")
public class StudentGrade{

    @EmbeddedId
    private StudentGradeId id;

    @ManyToOne
    @MapsId("gradeId")
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @ManyToOne
    @MapsId("studentRollnumber")
    @JoinColumn(name = "student_rollnumber")
    private Student student;
}
