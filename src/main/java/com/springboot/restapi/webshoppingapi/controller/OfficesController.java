package com.springboot.restapi.webshoppingapi.controller;

import com.springboot.restapi.webshoppingapi.dto.request.OfficesRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.Offices;
import com.springboot.restapi.webshoppingapi.service.OfficesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OfficesController {

    private OfficesService officesService;

    @GetMapping("/offices")
    public ResponseEntity<Response<List<Offices>>> getEmployees(){
        return new ResponseEntity<>(new Response<>("Offices", true, officesService.getOffices()), HttpStatus.OK);
    }

    @GetMapping("/offices/{id}")
    public ResponseEntity<Response<Offices>> getEmployeeById(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(new Response<>("Office "+id, true, officesService.getOfficesById(id)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PostMapping("/offices")
    public ResponseEntity<Response<Offices>> saveEmployee(@Valid @RequestBody OfficesRequest office){
        return new ResponseEntity<>(new Response<>("New Office Created", true, officesService.saveOffices(office)), HttpStatus.CREATED);
    }

    @PutMapping("/offices/{id}")
    public ResponseEntity<Response<Offices>> updateEmployee(@PathVariable Long id, @RequestBody Offices office){
        office.setOfficeCode(id);
        return new ResponseEntity<>(new Response<>("Updated", true, officesService.updateOffices(office)), HttpStatus.OK);
    }

    @DeleteMapping("/offices/{id}")
    public ResponseEntity<Offices> deleteEmployee(@PathVariable Long id){
        officesService.deleteOffices(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
