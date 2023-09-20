package com.springboot.restapi.webshoppingapi.dto.request;

public record ProductLinesRequest (

        String productLine,
        String textDescription,
        String htmlDescription,
        byte[] image
){}