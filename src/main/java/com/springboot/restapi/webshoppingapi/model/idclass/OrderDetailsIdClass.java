package com.springboot.restapi.webshoppingapi.model.idclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
@Getter
@EqualsAndHashCode
public class OrderDetailsIdClass implements Serializable {

    private Long orderNumber;
    private Long productCode;

}
