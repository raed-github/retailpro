package com.rsaad.retailpro.category.service;

import com.rsaad.retailpro.category.request.CreateCategoryRequest;
import com.rsaad.retailpro.category.request.UpdateCategoryRequest;
import com.rsaad.retailpro.category.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest request);

    CategoryResponse update(UUID id,
                            UpdateCategoryRequest request);

    CategoryResponse findById(UUID id);

    List<CategoryResponse> findAll();

    void delete(UUID id);

}