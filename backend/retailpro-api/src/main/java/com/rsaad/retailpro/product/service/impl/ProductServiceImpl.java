package com.rsaad.retailpro.product.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsaad.retailpro.category.entity.Category;
import com.rsaad.retailpro.category.repository.CategoryRepository;
import com.rsaad.retailpro.exception.BusinessException;
import com.rsaad.retailpro.exception.ResourceNotFoundException;
import com.rsaad.retailpro.product.entity.Product;
import com.rsaad.retailpro.product.mapper.ProductMapper;
import com.rsaad.retailpro.product.repository.ProductRepository;
import com.rsaad.retailpro.product.request.CreateProductRequest;
import com.rsaad.retailpro.product.request.UpdateProductRequest;
import com.rsaad.retailpro.product.response.ProductResponse;
import com.rsaad.retailpro.product.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ProductMapper mapper) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {

        validateUniqueFields(request.sku(), request.barcode());

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: " + request.categoryId()));

        Product product = mapper.toEntity(request);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        LOGGER.info(
                "Product created successfully. SKU={}",
                savedProduct.getSku());

        return mapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse update(
            UUID id,
            UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found: " + id));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: " + request.categoryId()));

        mapper.updateEntity(product, request);
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        LOGGER.info(
                "Product updated successfully. ID={}",
                id);

        return mapper.toResponse(updatedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found: " + id));

        return mapper.toResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {

        return productRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> search(String keyword) {

        return productRepository
                .findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found: " + id));

        productRepository.delete(product);

        LOGGER.info(
                "Product deleted successfully. ID={}",
                id);
    }

    private void validateUniqueFields(
            String sku,
            String barcode) {

        if (productRepository.existsBySku(sku)) {
            throw new BusinessException(
                    "Product SKU already exists: " + sku);
        }

        if (barcode != null
                && !barcode.isBlank()
                && productRepository.existsByBarcode(barcode)) {

            throw new BusinessException(
                    "Product barcode already exists: " + barcode);
        }
    }
}