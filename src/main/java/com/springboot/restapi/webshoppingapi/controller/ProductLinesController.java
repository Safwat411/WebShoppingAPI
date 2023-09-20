package com.springboot.restapi.webshoppingapi.controller;

import com.springboot.restapi.webshoppingapi.dto.request.ProductLinesRequest;
import com.springboot.restapi.webshoppingapi.dto.response.Response;
import com.springboot.restapi.webshoppingapi.model.ProductLines;
import com.springboot.restapi.webshoppingapi.service.ProductLinesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProductLinesController {


    private ProductLinesService productLinesService;

    @PostMapping("/product-lines/{productLine}/upload-image")
    public ResponseEntity<Response<Optional<ProductLines>>> uploadImage(@PathVariable String productLine, @RequestParam("file") MultipartFile file){
        try {
            Optional<ProductLines> upload = productLinesService.uploadImage(productLine, file);
            if(upload.isPresent()) {
                return new ResponseEntity<>(new Response<>("Image Uploaded Successfully", true, upload), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response<>("Image upload failed", false, null), HttpStatus.OK);
            }
        } catch (IOException e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @GetMapping("/product-lines")
    public ResponseEntity<Response<List<ProductLines>>> getProductLines(){
        return new ResponseEntity<>(new Response<>("Product Lines -----", true, productLinesService.getProductLines()), HttpStatus.OK);
    }

    @GetMapping("/product-lines/{productLine}")
    public ResponseEntity<Response<ProductLines>> getProductLineByName(@PathVariable String productLine){
        try{
            return new ResponseEntity<>(new Response<>("Product Line " + productLine, true, productLinesService.getProductLineByName(productLine)),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
        }
    }

    @PostMapping("/product-lines")
    public ResponseEntity<Response<ProductLines>> saveProductLine(@Valid @RequestBody ProductLinesRequest request){
        return new ResponseEntity<>(new Response<>("Product Line created", true, productLinesService.saveProductLine(request)), HttpStatus.CREATED);
    }

    @PutMapping("/product-lines/{plName}")
    public ResponseEntity<Response<ProductLines>> updateProductLine(@PathVariable String plName, @RequestBody ProductLines productLine) throws Exception {
        productLine.setProductLine(plName);
        return new ResponseEntity<>(new Response<>("Updated", true, productLinesService.updateProductLine(productLine)), HttpStatus.OK);
    }

    @DeleteMapping("/product-lines/{plName}")
    public ResponseEntity<ProductLines> deleteProductLine(@PathVariable String plName){
        productLinesService.deleteProductLine(plName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
