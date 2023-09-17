package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.EmployeesRequest;
import com.springboot.restapi.webshoppingapi.model.Employees;
import com.springboot.restapi.webshoppingapi.model.Offices;
import com.springboot.restapi.webshoppingapi.repository.EmployeesRepository;
import com.springboot.restapi.webshoppingapi.repository.OfficesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeesRepository employeesRepo;
    private OfficesRepository officesRepository;

    public List<Employees> getEmployees(){
        return employeesRepo.findAll();
    }

    public Employees getEmployeeById(Long id) throws Exception{
        Optional<Employees> employee =  employeesRepo.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        throw new Exception("Employee is not found for the ID: " + id);
    }

    public Employees saveEmployee(EmployeesRequest request) throws Exception {

        Employees employee = new Employees();

        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setJobTitle(request.jobTitle());
        employee.setExtension(request.extension());

        if(request.officeCode() != null){
            Optional<Offices> optionalOffices = this.officesRepository.findById(request.officeCode());
            Offices office;
            if (optionalOffices.isPresent()) {
                office = optionalOffices.get();
            } else {
                throw new Exception("Office not found for request: " + request.officeCode());
            }
            employee.setOfficeCode(office);
        }

        if (request.reportsTo() != null){
            Optional<Employees> optionalSupervisor = this.employeesRepo.findById(request.reportsTo());
            Employees supervisor;
            if (optionalSupervisor.isPresent()) {
                supervisor = optionalSupervisor.get();
            } else {
                throw new Exception("Supervisor not found for request: " + request.reportsTo());
            }
            employee.setReportsTo(supervisor);
        }

        return employeesRepo.save(employee);
    }

    public Employees updateEmployee(Employees employee){
        return employeesRepo.save(employee);
    }

    public void deleteEmployee(Long id){
        employeesRepo.deleteById(id);
    }
}
