package com.rsaad.retailpro.product.response;

import com.rsaad.retailpro.common.enums.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(

        UUID id,

        String sku,

        String barcode,

        String name,

        String description,

        BigDecimal costPrice,

        BigDecimal sellingPrice,

        ProductStatus status,

        UUID categoryId,

        String categoryName,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}