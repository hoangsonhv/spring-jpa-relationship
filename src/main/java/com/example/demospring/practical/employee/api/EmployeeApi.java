package com.example.demospring.practical.employee.api;

import com.example.demospring.practical.employee.repository.EmployeeRepository;
import com.example.demospring.practical.employee.service.EmployeeService;
import com.example.demospring.practical.employee.entity.Employee;
import com.example.demospring.practical.employee.entity.Employee;
import com.example.demospring.practical.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeApi {

    @Autowired
    protected EmployeeService employeeService;
    protected EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getList(@RequestParam(defaultValue = "") String name){
        if(name.length() > 0){
            return employeeRepository.findAllEmployees(name);
        }else {
            return employeeService.findAllEmployee();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee save(@RequestBody Employee employee){
        employeeService.save(employee);
        return employee;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Employee getDetail(@PathVariable int id){
        return employeeService.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public boolean delete(@PathVariable int id){
        employeeService.deleteById(id);
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee updateEmployee){
        Employee existing = employeeService.findById(id).get();
        existing.setName(updateEmployee.getName());
        existing.setWage(updateEmployee.getWage());
        employeeService.save(existing);
        return updateEmployee;
    }
}
