package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.OrderDetailsRequest;
import com.springboot.restapi.webshoppingapi.model.OrderDetails;
import com.springboot.restapi.webshoppingapi.model.Orders;
import com.springboot.restapi.webshoppingapi.model.Products;
import com.springboot.restapi.webshoppingapi.repository.OrderDetailsRepository;
import com.springboot.restapi.webshoppingapi.repository.OrdersRepository;
import com.springboot.restapi.webshoppingapi.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDetailsService {

    private OrderDetailsRepository orderDetailsRepo;
    private OrdersRepository ordersRepo;
    private ProductsRepository productsRepo;


    public List<OrderDetails> getOrderDetails(){
        return orderDetailsRepo.findAll();
    }

    public OrderDetails getOrderDetailsByOrderNumberAndProductCode(Long orderNumber, Long productCode) throws Exception {
        Optional<OrderDetails> orderDetails =  orderDetailsRepo.findByOrderNumberAndProductCode(orderNumber, productCode);
        if(orderDetails.isPresent()){
            return orderDetails.get();
        }
        throw new Exception("Order Details is not found ");
    }

    public OrderDetails saveOrderDetails(OrderDetailsRequest request) throws Exception {

        OrderDetails orderDetails = new OrderDetails();

        Optional<Orders> optionalOrders = this.ordersRepo.findById(request.orderNumber());
        Orders order;
        if (optionalOrders.isPresent()){
            order = optionalOrders.get();
        } else {
            throw new Exception("Order Details not found for request: " + request.orderNumber());
        }
        orderDetails.setOrderNumber(order);

        Optional<Products> optionalProducts = this.productsRepo.findById(request.productCode());
        Products product;
        if (optionalProducts.isPresent()){
            product = optionalProducts.get();
        } else {
            throw new Exception("Order Details not found for request: " + request.productCode());
        }
        orderDetails.setProductCode(product);

        orderDetails.setQuantityOrdered(request.quantityOrdered());
        orderDetails.setPriceEach(request.priceEach());
        orderDetails.setOrderLineNumber(request.orderLineNumber());

        return orderDetailsRepo.save(orderDetails);
    }

    public OrderDetails updateOrderDetails(Long orderNumber, Long productCode, OrderDetails orderDetails){
        Optional<Orders> optionalOrders = this.ordersRepo.findById(orderNumber);
        Orders order = optionalOrders.get();
        orderDetails.setOrderNumber(order);

        Optional<Products> optionalProducts = this.productsRepo.findById(productCode);
        Products product = optionalProducts.get();
        orderDetails.setProductCode(product);

        return orderDetailsRepo.save(orderDetails);
    }

    public void deleteOrderDetails(Long orderNumber, Long productCode) throws Exception {
        Optional<OrderDetails> orderDetailsOptional = orderDetailsRepo.findByOrderNumberAndProductCode(orderNumber, productCode);

        if (orderDetailsOptional.isPresent()) {
            orderDetailsRepo.delete(orderDetailsOptional.get());
        } else {
            throw new Exception("Order Details not found for request");
        }
    }
}
