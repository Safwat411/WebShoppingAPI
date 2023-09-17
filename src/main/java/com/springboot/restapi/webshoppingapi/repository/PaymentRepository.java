package com.springboot.restapi.webshoppingapi.repository;

import com.springboot.restapi.webshoppingapi.model.Payment;
import com.springboot.restapi.webshoppingapi.model.idclass.PaymentIdClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentIdClass> {

    @Query("SELECT p FROM Payment p WHERE p.customerNumber = :customerNumber AND p.checkNumber = :checkNumber")
    Optional<Payment> findByCustomerNumberAndCheckNumber(@Param("customerNumber") Long customerNumber, @Param("checkNumber") Long checkNumber);


}
