package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.ProductLinesRequest;
import com.springboot.restapi.webshoppingapi.model.ProductLines;
import com.springboot.restapi.webshoppingapi.repository.ProductLinesRepository;
import com.springboot.restapi.webshoppingapi.utils.ImageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductLinesService {


    private ProductLinesRepository productLinesRepo;

    public Optional<ProductLines> uploadImage(String productLine, MultipartFile file) throws IOException {

        Optional<ProductLines> optionalProductLines = productLinesRepo.findByProductLine(productLine);

        if(optionalProductLines.isPresent() && !file.isEmpty()){
            ProductLines productLines = optionalProductLines.get();
            productLines.setImage(ImageUtils.compressImage(file.getBytes()));
            return Optional.of(productLinesRepo.save(productLines));
        } else {
            return Optional.empty();
        }
    }

    public List<ProductLines> getProductLines(){
        return productLinesRepo.findAll();
    }

    public ProductLines getProductLineByName(String productLineName) throws Exception {
        Optional<ProductLines> productLine = this.productLinesRepo.findByProductLine(productLineName);
        if(productLine.isPresent()){
            return productLine.get();
        } else {
            throw new Exception("Product Line is not found");
        }
    }

    public ProductLines saveProductLine(ProductLinesRequest request){
        ProductLines productLine = new ProductLines();

        productLine.setProductLine(request.productLine());
        productLine.setTextDescription(request.textDescription());
        productLine.setImage(null);
        if(request.htmlDescription() != null) {
            productLine.setHtmlDescription(request.htmlDescription());
        }

        return productLinesRepo.save(productLine);
    }

    public ProductLines updateProductLine(ProductLines productLine) throws Exception {
        try {
            return productLinesRepo.save(productLine);
        } catch (Exception e){
            throw new Exception("Product Line not found");
        }
    }

    public void deleteProductLine(String productLine){
        productLinesRepo.deleteById(productLine);
    }

}
