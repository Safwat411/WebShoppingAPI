package com.springboot.restapi.webshoppingapi.controller;

import com.springboot.restapi.webshoppingapi.dto.request.ProductsRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.Products;
import com.springboot.restapi.webshoppingapi.service.ProductsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductsController {

    private ProductsService productsService;

    @GetMapping("/products")
    public ResponseEntity<Response<List<Products>>> getProducts(){
        return new ResponseEntity<>(new Response<>("Products ---", true, productsService.getProducts()), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Response<Products>> getProductById(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(new Response<>("Product for Id:" + id, true, productsService.getProductsById(id)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Response<Products>> saveProduct(@Valid @RequestBody ProductsRequest product) throws Exception {
        return new ResponseEntity<>(new Response<>("Product Created", true, productsService.saveProducts(product)), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Response<Products>> updateProduct(@PathVariable Long id, @RequestBody Products product){
        product.setProductCode(id);
        return new ResponseEntity<>(new Response<>("Updated", true, productsService.updateProducts(product)), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Products> deleteEmployee(@PathVariable Long id){
        productsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
