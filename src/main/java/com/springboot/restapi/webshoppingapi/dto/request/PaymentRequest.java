package com.springboot.restapi.webshoppingapi.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record PaymentRequest (
        Long customerNumber,
        Long checkNumber,
        LocalDateTime paymentDate,
        BigDecimal amount
) {}
