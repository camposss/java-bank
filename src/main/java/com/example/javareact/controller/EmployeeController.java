package com.example.javareact.controller;

import com.example.javareact.exception.ResourceNotFoundException;
import com.example.javareact.model.Employee;
import com.example.javareact.repository.EmployeeRepository;
import com.example.javareact.repository.PagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PagingRepository pagingRepository;

    @GetMapping("/employees")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public List<Employee> getAllEmployees(Employee employee){
        return employeeRepository.findAll();

    }

    //Get 10 Employees per Page and Sort by Name
    @GetMapping("/employees/page/{page}")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public Page<Employee> getEmployees(@PathVariable(value="page") int page){
        return pagingRepository.findAll(new PageRequest(page, 10, Sort.Direction.ASC, "name"));

    }
    //Add an Employee
    //Since we don't include the values from front end in the url path, we can simply use the request body
    @PostMapping("/employees")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public Employee addEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //Grabbing a single employee using id in path
    @GetMapping("/employees/{id}")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public Employee getEmployeeById(@PathVariable(value="id") Long employeeId){
        return employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    //Updating an Employee
    @PutMapping("employees/{id}")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public Employee updateEmployee(@PathVariable(value="id") Long employeeId, @Valid @RequestBody Employee employeeInfo){
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()->new ResourceNotFoundException("Employee", "id", employeeId));
        employee.setName(employeeInfo.getName());
        employee.setPhoneNumber(employeeInfo.getPhoneNumber());
        employee.setSupervisor(employeeInfo.getSupervisor());
        Employee updatedEmployee= employeeRepository.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employees/{id}")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public ResponseEntity<?> deleteEmployee(@PathVariable(value="id") Long employeeId){
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "id", "employeeId"));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }


}
