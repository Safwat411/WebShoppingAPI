package com.springboot.restapi.webshoppingapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductLinesRequest {

    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private String image;


}
