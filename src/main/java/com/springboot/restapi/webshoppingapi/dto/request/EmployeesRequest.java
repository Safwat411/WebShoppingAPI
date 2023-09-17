package com.springboot.restapi.webshoppingapi.dto.request;


public record EmployeesRequest (

     Long employeeNumber,
     String firstName,
     String lastName,
     String extension,
     String email,
     Long officeCode,
     Long reportsTo,
     String jobTitle
){}
