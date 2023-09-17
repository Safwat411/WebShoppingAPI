package com.springboot.restapi.webshoppingapi.repository;

import com.springboot.restapi.webshoppingapi.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
}
