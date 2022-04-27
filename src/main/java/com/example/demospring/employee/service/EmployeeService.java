package com.example.demospring.employee.service;

import com.example.demospring.employee.entity.Employee;
import com.example.demospring.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(String name){
        return employeeRepository.findAllEmployees(name);
    }

    public List<Employee> findAllEmployee(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Integer id){
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteById(Integer id){
        employeeRepository.deleteById(id);
    }
}
