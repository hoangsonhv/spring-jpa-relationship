package com.example.demospring.manytomany.api;

import com.example.demospring.manytomany.entity.Grade;
import com.example.demospring.manytomany.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/grades")
public class GradeApi {

    @Autowired
    GradeService gradeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Grade>> getList(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "10") int limit)
    {
        return ResponseEntity.ok(gradeService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Grade> getDetail(@PathVariable Integer id){
        Optional<Grade> gradeOptional = gradeService.findById(id);
        if (!gradeOptional.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gradeOptional.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> store(@RequestBody Grade grade){
        return ResponseEntity.ok(gradeService.save(grade));
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Grade> update(@PathVariable Integer id, @RequestBody Grade grade){
        Optional<Grade> gradeOptional = gradeService.findById(id);
        if (!gradeOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Grade exitsGrade = gradeOptional.get();
        exitsGrade.setName(grade.getName());
        return ResponseEntity.ok(gradeService.save(exitsGrade));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Grade> delete(@PathVariable Integer id){
        if (!gradeService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        gradeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
