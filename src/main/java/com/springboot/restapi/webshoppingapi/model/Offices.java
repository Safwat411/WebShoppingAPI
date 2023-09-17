package com.springboot.restapi.webshoppingapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Offices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officeCode;

    private String city;


    private String phone;


    private String addressLine1;


    private String addressLine2;


    private String  state;


    private String country;


    private String postalCode;


    private String territory;
}
