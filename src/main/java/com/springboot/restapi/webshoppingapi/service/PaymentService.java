package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.PaymentRequest;
import com.springboot.restapi.webshoppingapi.model.Customers;
import com.springboot.restapi.webshoppingapi.model.Payment;
import com.springboot.restapi.webshoppingapi.repository.CustomersRepository;
import com.springboot.restapi.webshoppingapi.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentRepository paymentRepo;
    private CustomersRepository customersRepo;

    public List<Payment> getPayments(){
        return paymentRepo.findAll();
    }

    public Payment getPaymentsByCustomerNumberAndCheckNumber(Long customerNumber, Long checkNumber) throws Exception {
        Optional<Payment> payment = this.paymentRepo.findByCustomerNumberAndCheckNumber(customerNumber, checkNumber);
        if(payment.isPresent()){
            return payment.get();
        }
        throw  new Exception("Payment is not found");
    }

    public Payment savePayment(PaymentRequest request) throws Exception {
        Payment payment = new Payment();

        Optional<Customers> optionalCustomers = this.customersRepo.findById(request.customerNumber());
        Customers customer;
        if(optionalCustomers.isPresent()){
            customer = optionalCustomers.get();
        } else {
            throw new Exception("Customer is not found");
        }
        payment.setCustomerNumber(customer);

        payment.setCheckNumber(request.checkNumber());
        payment.setPaymentDate(request.paymentDate());
        payment.setAmount(request.amount());

        return paymentRepo.save(payment);
    }

    public Payment updatePayment(Payment payment){
        return paymentRepo.save(payment);
    }

    public void deletePayment(Long customerNumber, Long checkNumber) throws Exception {
        Optional<Payment> optionalPayment = paymentRepo.findByCustomerNumberAndCheckNumber(customerNumber, checkNumber);
        if(optionalPayment.isPresent()){
            paymentRepo.delete(optionalPayment.get());
        } else {
            throw new Exception("Payment is not found");
        }
    }

}
