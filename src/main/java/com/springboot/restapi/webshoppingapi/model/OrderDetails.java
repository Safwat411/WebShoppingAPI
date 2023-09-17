package com.springboot.restapi.webshoppingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.restapi.webshoppingapi.model.idclass.OrderDetailsIdClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table
@IdClass(OrderDetailsIdClass.class)
public class OrderDetails {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders orderNumber;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Products productCode;

    private int quantityOrdered;

    private BigDecimal priceEach;

    private int orderLineNumber;
}
