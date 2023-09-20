package com.springboot.restapi.webshoppingapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCode;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "productLine")
    private ProductLines productLine;

    private String productScale;

    private String productVendor;

    private String productDescription;

    private int amountInStock;

    private BigDecimal buyPrice;

    private BigDecimal MSRP;

    @OneToMany(mappedBy = "productCode", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetails> orderDetails = new ArrayList<>();

}
