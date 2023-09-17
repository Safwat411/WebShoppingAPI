package com.springboot.restapi.webshoppingapi.controller;

import com.springboot.restapi.webshoppingapi.dto.request.EmployeesRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.Employees;
import com.springboot.restapi.webshoppingapi.service.EmployeesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping("/employees")
    public ResponseEntity<Response<List<Employees>>> getEmployees(){
        return new ResponseEntity<>(new Response<>("Employees ---", true, employeesService.getEmployees()), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Response<Employees>> getEmployeeById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(new Response<>("Employee " + id, true, employeesService.getEmployeeById(id)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<Response<Employees>> saveEmployee(@Valid @RequestBody EmployeesRequest employee) throws Exception {
        return new ResponseEntity<>(new Response<>("Employee Created", true, employeesService.saveEmployee(employee)), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Response<Employees>> updateEmployee(@PathVariable Long id, @RequestBody Employees employee){
        employee.setEmployeeNumber(id);
        return new ResponseEntity<>(new Response<>("Updated", true, employeesService.updateEmployee(employee)), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employees> deleteEmployee(@PathVariable Long id){
        employeesService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
