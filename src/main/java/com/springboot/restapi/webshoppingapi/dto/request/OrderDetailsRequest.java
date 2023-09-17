package com.springboot.restapi.webshoppingapi.dto.request;


import java.math.BigDecimal;


public record OrderDetailsRequest (

        Long orderNumber,
        Long productCode,
        int quantityOrdered,
        BigDecimal priceEach,
        int orderLineNumber
){}

