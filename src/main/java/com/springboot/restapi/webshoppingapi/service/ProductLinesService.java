package com.springboot.restapi.webshoppingapi.service;

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
public class ProductLinesService {
    @Id
    private String productLine;

    private String textDescription;

    private String htmlDescription;

}
