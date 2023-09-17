package com.springboot.restapi.webshoppingapi.dto.request;


import java.math.BigDecimal;

public record ProductsRequest (

        Long productCode,
        String productName,
        String productLine,
        String productScale,
        String productVendor,
        String productDescription,
        int amountInStock,
        BigDecimal buyPrice,
        BigDecimal MSRP

){}