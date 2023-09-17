package com.springboot.restapi.webshoppingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.restapi.webshoppingapi.model.idclass.PaymentIdClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table
@IdClass(PaymentIdClass.class)
public class Payment {

    @Id
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    @JsonBackReference
    private Customers customerNumber;

    @Id
    private Long checkNumber;

    private LocalDateTime paymentDate;

    private BigDecimal amount;


}
