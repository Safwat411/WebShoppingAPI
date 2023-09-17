package com.springboot.restapi.webshoppingapi.controller;

import com.springboot.restapi.webshoppingapi.dto.request.CustomersRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.Customers;
import com.springboot.restapi.webshoppingapi.service.CustomersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class CustomersController {

    private CustomersService customersService;


    @GetMapping("/customers")
    public ResponseEntity<Response<List<Customers>>> getCustomers(){

        return new ResponseEntity<>(new Response<>("All customers", true, customersService.getCustomers()), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Response<Customers>> getCustomersById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new Response<>("customer ---", true,customersService.getCustomersById(id) ), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(new Response<>(exception.getMessage(), false,null), HttpStatus.OK);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Response<Customers>> saveCustomer(@Valid @RequestBody CustomersRequest customer) throws Exception {
        return new ResponseEntity<>(new Response<>("New Customer Created",true, customersService.saveCustomer(customer)), HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Response<Customers>> updateCustomer(@PathVariable Long id, @RequestBody Customers customer){
        customer.setCustomerNumber(id);
        return new ResponseEntity<>(new Response<>("Updated", true, customersService.updateCustomer(customer)), HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customers> deleteCustomer(@PathVariable Long id){
        customersService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
