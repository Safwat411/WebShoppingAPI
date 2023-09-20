package com.springboot.restapi.webshoppingapi.controller;


import com.springboot.restapi.webshoppingapi.dto.request.OrderDetailsRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.OrderDetails;
import com.springboot.restapi.webshoppingapi.service.OrderDetailsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderDetailsController {

    private OrderDetailsService orderDetailsService;


    @GetMapping("/order-details")
    public ResponseEntity<Response<List<OrderDetails>>> getOrders(){
        return new ResponseEntity<>(new Response<>("Orders Details ---", true, orderDetailsService.getOrderDetails()), HttpStatus.OK);
    }

    @GetMapping("/order-details/by-param")
    public ResponseEntity<Response<OrderDetails>> getOrdersById(@RequestParam Long orderNumber,@RequestParam Long productCode){
        try {
            return new ResponseEntity<>(new Response<>("Order Details: ", true, orderDetailsService.getOrderDetailsByOrderNumberAndProductCode(orderNumber, productCode)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PostMapping("/order-details")
    public ResponseEntity<Response<OrderDetails>> saveOrders(@Valid @RequestBody OrderDetailsRequest order) throws Exception {
        try {
            return new ResponseEntity<>(new Response<>("Order Details Created", true, orderDetailsService.saveOrderDetails(order)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PutMapping("/order-details")
    public ResponseEntity<Response<OrderDetails>> updateOrders(@RequestParam Long orderNumber,@RequestParam Long productCode, @RequestBody OrderDetails orderDetails){
        return new ResponseEntity<>(new Response<>("Updated", true, orderDetailsService.updateOrderDetails(orderNumber, productCode, orderDetails)), HttpStatus.OK);
    }

    @DeleteMapping("/order-details")
    public ResponseEntity<OrderDetails> deleteOrders(@RequestParam Long orderNumber,@RequestParam Long productCode) throws Exception {
        orderDetailsService.deleteOrderDetails(orderNumber, productCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
