package com.example.demospring.manytomany.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StudentGradeId  implements Serializable {
    @Column(name = "student_rollnumber")
    private String studentRollnumber;
    @Column(name = "grade_id")
    private int gradeId;

}
