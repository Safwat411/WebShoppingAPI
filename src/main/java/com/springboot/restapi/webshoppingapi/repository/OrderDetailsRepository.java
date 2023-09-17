package com.springboot.restapi.webshoppingapi.repository;

import com.springboot.restapi.webshoppingapi.model.OrderDetails;
import com.springboot.restapi.webshoppingapi.model.idclass.OrderDetailsIdClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsIdClass> {

    @Query("SELECT od FROM OrderDetails od WHERE od.orderNumber.orderNumber = :orderNumber AND od.productCode.productCode = :productCode")
    Optional<OrderDetails> findByOrderNumberAndProductCode(@Param("orderNumber") Long orderNumber, @Param("productCode") Long productCode);

}

