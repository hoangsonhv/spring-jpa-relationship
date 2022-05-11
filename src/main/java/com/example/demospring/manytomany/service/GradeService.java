package com.example.demospring.manytomany.service;

import com.example.demospring.manytomany.entity.Grade;
import com.example.demospring.manytomany.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findAll(){
        return gradeRepository.findAll();
    }

    public Optional<Grade> findById(Integer id){
        return gradeRepository.findById(id);
    }

    public Grade save(Grade grade){
        return gradeRepository.save(grade);
    }

    public void deleteById(Integer id){
        gradeRepository.deleteById(id);
    }
}
