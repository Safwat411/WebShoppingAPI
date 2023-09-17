package com.springboot.restapi.webshoppingapi.controller;


import com.springboot.restapi.webshoppingapi.dto.request.OrdersRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.Orders;
import com.springboot.restapi.webshoppingapi.service.OrdersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrdersController {

    private OrdersService ordersService;


    @GetMapping("/orders")
    public ResponseEntity<Response<List<Orders>>> getOrders(){
        return new ResponseEntity<>(new Response<>("Orders ---", true, ordersService.getOrders()), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Response<Orders>> getOrdersById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new Response<>("Order: "+id, true, ordersService.getOrdersById(id)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Response<Orders>> saveOrders(@Valid @RequestBody OrdersRequest order) throws Exception {
        return new ResponseEntity<>(new Response<>("Order Created", true, ordersService.saveOrders(order)), HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Response<Orders>> updateOrders(@PathVariable Long id, @RequestBody Orders order){
        order.setOrderNumber(id);
        return new ResponseEntity<>(new Response<>("Updated", true, ordersService.updateOrders(order)), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Orders> deleteOrders(@PathVariable Long id){
        ordersService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
