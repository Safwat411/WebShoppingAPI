package com.springboot.restapi.webshoppingapi.repository;

import com.springboot.restapi.webshoppingapi.model.ProductLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductLinesRepository extends JpaRepository<ProductLines, Long> {
    @Query("SELECT pl FROM ProductLines pl WHERE pl.productLine = :productLine")
    Optional<ProductLines> findByProductLine(@Param("productLine") String productLine);
}

