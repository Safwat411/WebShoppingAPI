package com.springboot.restapi.webshoppingapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class ProductLines {

    @Id
    private String productLine;

    private String textDescription;

    private String htmlDescription;

    private String image;


}
