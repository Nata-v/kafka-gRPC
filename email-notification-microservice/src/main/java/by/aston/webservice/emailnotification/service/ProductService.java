package by.aston.webservice.emailnotification.service;

import by.aston.webservice.emailnotification.dto.CreateProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct (CreateProductDto createProductDto) throws ExecutionException, InterruptedException;

}
