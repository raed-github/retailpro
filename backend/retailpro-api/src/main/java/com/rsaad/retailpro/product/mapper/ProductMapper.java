package com.rsaad.retailpro.product.mapper;

import org.springframework.stereotype.Component;

import com.rsaad.retailpro.product.entity.Product;
import com.rsaad.retailpro.product.request.CreateProductRequest;
import com.rsaad.retailpro.product.request.UpdateProductRequest;
import com.rsaad.retailpro.product.response.ProductResponse;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest request) {

        Product product = new Product();

        product.setSku(request.sku());
        product.setBarcode(request.barcode());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setCostPrice(request.costPrice());
        product.setSellingPrice(request.sellingPrice());

        return product;
    }

    public ProductResponse toResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getSku(),
                product.getBarcode(),
                product.getName(),
                product.getDescription(),
                product.getCostPrice(),
                product.getSellingPrice(),
                product.getStatus(),
                product.getCategory() != null
                        ? product.getCategory().getId()
                        : null,
                product.getCategory() != null
                        ? product.getCategory().getName()
                        : null,
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public void updateEntity(
            Product product,
            UpdateProductRequest request) {

        product.setSku(request.sku());
        product.setBarcode(request.barcode());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setCostPrice(request.costPrice());
        product.setSellingPrice(request.sellingPrice());
    }
}