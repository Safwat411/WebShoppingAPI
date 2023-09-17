package com.springboot.restapi.webshoppingapi.dto.request;

public record CustomersRequest (
     Long customerNumber,
     String customerName,
     String contactFirstName,
     String contactLastName,
     String phone,
     String addressLine1,
     String addressLine2,
     String city,
     String state,
     String postalCode,
     String country,
     Long salesRepEmployeeNumber,
     String creditLimit
){
}
