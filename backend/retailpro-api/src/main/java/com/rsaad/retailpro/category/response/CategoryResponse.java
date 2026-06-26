package com.rsaad.retailpro.category.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponse(

        UUID id,

        String name,

        String description,

        UUID parentCategoryId,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}