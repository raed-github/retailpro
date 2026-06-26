package com.rsaad.retailpro.category.mapper;

import com.rsaad.retailpro.category.entity.Category;
import com.rsaad.retailpro.category.request.CreateCategoryRequest;
import com.rsaad.retailpro.category.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequest request) {

        Category category = new Category();

        category.setName(request.name());
        category.setDescription(request.description());
        category.setParentCategoryId(request.parentCategoryId());

        return category;
    }

    public CategoryResponse toResponse(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getParentCategoryId(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}