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
public class ProductLines {

    @Id
    private String productLine;

    private String textDescription;

    private String htmlDescription;

    @Lob
    @Column(name = "image", length = 1000)
    private byte[] image;
}
