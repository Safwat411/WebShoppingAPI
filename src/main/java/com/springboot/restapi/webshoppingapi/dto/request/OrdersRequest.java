package com.springboot.restapi.webshoppingapi.dto.request;


import java.time.LocalDate;


public record OrdersRequest (
        Long orderNumber,
        LocalDate orderDate,
        LocalDate requiredDate,
        LocalDate shippedDate,
        String status,
        String comments,
        Long customerNumber
){}


