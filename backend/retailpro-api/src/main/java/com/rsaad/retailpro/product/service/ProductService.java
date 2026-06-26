package com.rsaad.retailpro.product.service;

import com.rsaad.retailpro.product.request.CreateProductRequest;
import com.rsaad.retailpro.product.request.UpdateProductRequest;
import com.rsaad.retailpro.product.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    ProductResponse update(UUID id,
                           UpdateProductRequest request);

    ProductResponse findById(UUID id);

    List<ProductResponse> findAll();

    List<ProductResponse> search(String keyword);

    void delete(UUID id);
}