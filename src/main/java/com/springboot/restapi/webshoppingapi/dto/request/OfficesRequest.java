package com.springboot.restapi.webshoppingapi.dto.request;


public record OfficesRequest (
     Long officeCode,
     String city,
     String phone,
     String addressLine1,
     String addressLine2,
     String  state,
     String country,
     String postalCode,
     String territory
){}