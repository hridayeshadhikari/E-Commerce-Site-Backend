package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Repository.ProductRepository;
import com.ecommerceproject.Request.CreateProductRequest;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;
    @Override
    public Product createProduct(CreateProductRequest request) {
        Product product=new Product();
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscountedPrice(request.getDiscountedPrice());
        product.setDiscountPercent(request.getDiscountPercent());
        product.setBrand(request.getBrand());
        product.setColor(request.getColor());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setSize(request.getSize());
        product.setCategory(request.getCategory());
        product.setTimeStamp(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product findProductById(Long productId) {
        return null;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> color, List<String> size, Integer minPrice, Integer maxPrice, Integer minDiscount, Integer maxDiscount, String stock, String sort, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
