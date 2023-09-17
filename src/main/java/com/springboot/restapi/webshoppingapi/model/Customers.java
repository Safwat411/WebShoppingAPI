package com.springboot.restapi.webshoppingapi.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@Entity
public class Customers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNumber;

    private String customerName;


    private String contactFirstName;


    private String contactLastName;


    private String phone;


    private String addressLine1;


    private String addressLine2;


    private String city;


    private String state;


    private String postalCode;


    private String country;


    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber")
    private Employees salesRepEmployeeNumber;

    private String creditLimit;

    @OneToMany(mappedBy = "customerNumber", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Payment> payments = new ArrayList<>();
}
