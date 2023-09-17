package com.springboot.restapi.webshoppingapi.repository;

import com.springboot.restapi.webshoppingapi.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
