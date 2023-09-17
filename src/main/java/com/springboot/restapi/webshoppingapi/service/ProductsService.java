package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.ProductsRequest;
import com.springboot.restapi.webshoppingapi.model.ProductLines;
import com.springboot.restapi.webshoppingapi.model.Products;
import com.springboot.restapi.webshoppingapi.repository.ProductLinesRepository;
import com.springboot.restapi.webshoppingapi.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsService {

    private ProductsRepository productsRepo;
    private ProductLinesRepository productLinesRepo;

    public List<Products> getProducts(){
        return productsRepo.findAll();
    }

    public Products getProductsById(Long id) throws Exception {
        Optional<Products> product =  productsRepo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw new Exception("Product is not found for the ID: " + id);
    }

    public Products saveProducts(ProductsRequest request) throws Exception {

        Products product = new Products();

        product.setProductName(request.productName());
        if (request.productLine() != null){
            Optional<ProductLines> optionalProductLines = this.productLinesRepo.findByProductLine(request.productLine());
            ProductLines productLines;
            if(optionalProductLines.isPresent()){
                productLines = optionalProductLines.get();
            } else {
                throw new Exception("Product line not found for request: " + request.productLine());
            }
            product.setProductLine(productLines);
        }
        product.setProductScale(request.productScale());
        product.setProductVendor(request.productVendor());
        product.setProductDescription(request.productDescription());
        product.setAmountInStock(request.amountInStock());
        product.setBuyPrice(request.buyPrice());
        product.setMSRP(request.MSRP());


        return productsRepo.save(product);
    }

    public Products updateProducts(Products product){
        return productsRepo.save(product);
    }

    public void deleteProduct(Long id){
        productsRepo.deleteById(id);
    }
}
