package com.springboot.restapi.webshoppingapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;


    @CreationTimestamp
    private LocalDate orderDate;

    private LocalDate requiredDate;

    private LocalDate shippedDate;

    private String status;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customers customerNumber;

    @OneToMany(mappedBy = "orderNumber", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetails> orderDetails = new ArrayList<>();

//    @OneToOne(mappedBy = "orderNumber", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private OrderDetails orderDetails = new OrderDetails();
}
