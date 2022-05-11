package com.example.demospring.manytomany.service;

import com.example.demospring.manytomany.entity.Grade;
import com.example.demospring.manytomany.entity.Student;
import com.example.demospring.manytomany.entity.StudentGrade;
import com.example.demospring.manytomany.entity.StudentGradeId;
import com.example.demospring.manytomany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(String rollNumber){
        return studentRepository.findById(rollNumber);
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public void deleteById(String rollNumber){
        studentRepository.deleteById(rollNumber);
    }

    public boolean addStudentToGrade(Student student, Grade grade) {
        try{
            Set<StudentGrade> studentGrades = student.getStudentGrades();
            if (studentGrades == null){
                studentGrades = new HashSet<>();
            }
            StudentGrade studentGrade = new StudentGrade();
            studentGrade.setId(new StudentGradeId(student.getRollNumber(), grade.getId()));
            studentGrade.setGrade(grade);
            studentGrade.setStudent(student);
            studentGrades.add(studentGrade);
            student.setStudentGrades(studentGrades);
            studentRepository.save(student);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }
}
