package com.example.demospring.manytomany.api;

import com.example.demospring.manytomany.entity.Grade;
import com.example.demospring.manytomany.entity.Student;
import com.example.demospring.manytomany.service.GradeService;
import com.example.demospring.manytomany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentApi {

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getList(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int limit)
    {
        return ResponseEntity.ok(studentService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{rollNumber}")
    public ResponseEntity<Student> getDetail(@PathVariable String rollNumber){
        Optional<Student> studentOptional = studentService.findById(rollNumber);
        if (!studentOptional.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentOptional.get());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add-grade")
    public ResponseEntity<?> addStudenToGrade(
            @RequestParam String studentRollnumber,
            @RequestParam int gradeId){
        Optional<Student> studentOptional = studentService.findById(studentRollnumber);
        Optional<Grade> gradeOptional = gradeService.findById(gradeId);
        if (!studentOptional.isPresent() || !gradeOptional.isPresent()){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        boolean result = studentService.addStudentToGrade(studentOptional.get(), gradeOptional.get());
        if (!result){
            return new ResponseEntity<>("Add student error.", HttpStatus.INSUFFICIENT_STORAGE);
        }
        return new ResponseEntity<>("Add student success.", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> store(@RequestBody Student student){
        return ResponseEntity.ok(studentService.save(student));
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{rollNumber}")
    public ResponseEntity<Student> update(@PathVariable String rollNumber, @RequestBody Student student){
        Optional<Student> studentOptional = studentService.findById(rollNumber);
        if (!studentOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Student exitsStudent = studentOptional.get();
        exitsStudent.setFullName(student.getFullName());
        exitsStudent.setGender(student.getGender());
        exitsStudent.setStatus(student.getStatus());
        return ResponseEntity.ok(studentService.save(exitsStudent));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{rollNumber}")
    public ResponseEntity<Student> delete(@PathVariable String rollNumber){
        if (!studentService.findById(rollNumber).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        studentService.deleteById(rollNumber);
        return ResponseEntity.ok().build();
    }
}
