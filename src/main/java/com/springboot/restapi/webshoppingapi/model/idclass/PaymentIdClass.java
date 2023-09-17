package com.springboot.restapi.webshoppingapi.model.idclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
@Getter
@EqualsAndHashCode
public class PaymentIdClass implements Serializable {

    private Long customerNumber;
    private Long checkNumber;
}
