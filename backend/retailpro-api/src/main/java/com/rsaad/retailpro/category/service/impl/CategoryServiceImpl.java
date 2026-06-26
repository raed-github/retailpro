package com.rsaad.retailpro.category.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsaad.retailpro.category.entity.Category;
import com.rsaad.retailpro.category.mapper.CategoryMapper;
import com.rsaad.retailpro.category.repository.CategoryRepository;
import com.rsaad.retailpro.category.request.CreateCategoryRequest;
import com.rsaad.retailpro.category.request.UpdateCategoryRequest;
import com.rsaad.retailpro.category.response.CategoryResponse;
import com.rsaad.retailpro.category.service.CategoryService;
import com.rsaad.retailpro.exception.BusinessException;
import com.rsaad.retailpro.exception.ResourceNotFoundException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(
            CategoryRepository repository,
            CategoryMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

        if (repository.existsByName(request.name())) {
            throw new BusinessException(
                    "Category already exists: " + request.name());
        }

        Category category = mapper.toEntity(request);

        Category savedCategory = repository.save(category);

        return mapper.toResponse(savedCategory);
    }

    @Override
    public CategoryResponse update(
            UUID id,
            UpdateCategoryRequest request) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: " + id));

        category.setName(request.name());
        category.setDescription(request.description());
        category.setParentCategoryId(request.parentCategoryId());

        Category updatedCategory = repository.save(category);

        return mapper.toResponse(updatedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findById(UUID id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: " + id));

        return mapper.toResponse(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Category not found: " + id);
        }

        repository.deleteById(id);
    }
}