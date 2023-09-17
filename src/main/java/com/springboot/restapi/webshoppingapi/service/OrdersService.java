package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.OrdersRequest;
import com.springboot.restapi.webshoppingapi.model.Customers;
import com.springboot.restapi.webshoppingapi.model.Orders;
import com.springboot.restapi.webshoppingapi.repository.CustomersRepository;
import com.springboot.restapi.webshoppingapi.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrdersService {

    private OrdersRepository ordersRepo;
    private CustomersRepository customersRepo;

    public List<Orders> getOrders(){
        return ordersRepo.findAll();
    }

    public Orders getOrdersById(Long id) throws Exception {
        Optional<Orders> order =  ordersRepo.findById(id);
        if(order.isPresent()){
            return order.get();
        }
        throw new Exception("Order is not found for the ID: " + id);
    }

    public Orders saveOrders(OrdersRequest request) throws Exception {

        Orders order = new Orders();

        order.setOrderDate(request.orderDate());
        order.setRequiredDate(request.requiredDate());
        order.setShippedDate(request.shippedDate());
        order.setStatus(request.status());
        order.setComments(request.comments());

        Optional<Customers> optionalCustomers = this.customersRepo.findById(request.customerNumber());
        Customers customer;
        if(optionalCustomers.isPresent()){
            customer = optionalCustomers.get();
        } else {
            throw new Exception("Customer not found for request:" + request.customerNumber());
        }
        order.setCustomerNumber(customer);

        return ordersRepo.save(order);
    }

    public Orders updateOrders(Orders order){
        return ordersRepo.save(order);
    }

    public void deleteOrder(Long id){
        ordersRepo.deleteById(id);
    }
}
