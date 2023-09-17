package com.springboot.restapi.webshoppingapi.dto.response;


public record Response<T>(
        String message,
        boolean status,
        T data
) {

}
