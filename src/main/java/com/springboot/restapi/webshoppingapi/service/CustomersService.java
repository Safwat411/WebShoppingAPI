package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.CustomersRequest;
import com.springboot.restapi.webshoppingapi.model.Customers;
import com.springboot.restapi.webshoppingapi.model.Employees;
import com.springboot.restapi.webshoppingapi.repository.CustomersRepository;
import com.springboot.restapi.webshoppingapi.repository.EmployeesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomersService {

    private CustomersRepository customersRepo;
    private EmployeesRepository employeesRepository;

    public List<Customers> getCustomers() {
        return customersRepo.findAll();
    }


    public Customers getCustomersById(Long id) throws Exception {
        Optional<Customers> customer = customersRepo.findById(id);
        if (customer.isPresent()){
            return customer.get();
        }
        throw new Exception("Customer is not found for the ID: " + id);
    }


    public Customers saveCustomer(CustomersRequest request) throws Exception {
        Customers customer = new Customers();
        customer.setCustomerNumber(request.customerNumber());
        customer.setCustomerName(request.customerName());
        customer.setCity(request.city());
        customer.setState(request.state());
        customer.setCountry(request.country());
        customer.setPhone(request.phone());
        customer.setAddressLine1(request.addressLine1());
        customer.setAddressLine2(request.addressLine2());
        customer.setContactFirstName(request.contactFirstName());
        customer.setContactLastName(request.contactLastName());
        customer.setPostalCode(request.postalCode());
        customer.setCreditLimit(request.creditLimit());

        if(request.salesRepEmployeeNumber() != null) {
            Optional<Employees> optionalEmployees = this.employeesRepository.findById(request.salesRepEmployeeNumber());
            Employees employees;
            if (optionalEmployees.isPresent()) {
                employees = optionalEmployees.get();
            } else {
                throw new Exception("Sales Rep. Employee not found for request: " + request.salesRepEmployeeNumber());
            }
            customer.setSalesRepEmployeeNumber(employees);
        }

        return customersRepo.save(customer);
    }

    public Customers updateCustomer(Customers customer){
      return customersRepo.save(customer);
    }

    public void deleteCustomer(Long id){
        customersRepo.deleteById(id);
    }


}
