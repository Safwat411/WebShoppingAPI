package com.springboot.restapi.webshoppingapi.controller;

import com.springboot.restapi.webshoppingapi.dto.request.PaymentRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.Customers;
import com.springboot.restapi.webshoppingapi.model.Payment;
import com.springboot.restapi.webshoppingapi.repository.CustomersRepository;
import com.springboot.restapi.webshoppingapi.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;
    private CustomersRepository customersRepo;

    @GetMapping("/payments")
    public ResponseEntity<Response<List<Payment>>> getPayments(){
        return new ResponseEntity<>(new Response<>("Payments ---", true, paymentService.getPayments()), HttpStatus.OK);
    }

    @GetMapping("/payments/by-param")
    public ResponseEntity<Response<Payment>> getPaymentByCustomerNumberAndCheckNumber(@RequestParam Long customerNumber, @RequestParam Long checkNumber){
        try{
            return new ResponseEntity<>(new Response<>("Payment", true, paymentService.getPaymentsByCustomerNumberAndCheckNumber(customerNumber,checkNumber)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PostMapping("/payments")
    public ResponseEntity<Response<Payment>> savePayment(@Valid @RequestBody PaymentRequest request){
        try{
            Payment savedPayment = paymentService.savePayment(request);
            return new ResponseEntity<>(new Response<>("Payment Created", true, savedPayment), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PutMapping("/payments")
    public ResponseEntity<Response<Payment>> updatePayment(@RequestParam Long customerNumber, @RequestParam Long checkNumber, @RequestBody Payment payment) throws Exception {
        Optional<Customers> optionalCustomer = this.customersRepo.findById(customerNumber);
        Customers customer;
        if(optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
        } else {
            throw new Exception("Customer is not found");
        }
        payment.setCustomerNumber(customer);

        return new ResponseEntity<>(new Response<>("Updated" ,true, paymentService.updatePayment(payment)), HttpStatus.OK);
    }

    @DeleteMapping("/payments")
    public ResponseEntity<Payment> deletePayment(@RequestParam Long customerNumber, @RequestParam Long checkNumber) throws Exception {
        paymentService.deletePayment(customerNumber, checkNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
