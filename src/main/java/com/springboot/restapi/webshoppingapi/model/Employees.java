package com.springboot.restapi.webshoppingapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Employees {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeNumber;

    private String firstName;


    private String lastName;


    private String extension;

    @Email
    private String email;


    @ManyToOne
    @JoinColumn(name = "officeCode")
    private Offices officeCode;


    @ManyToOne
    @JoinColumn(name = "reportsTo")
    private Employees reportsTo;


    private String jobTitle;
}
