package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest request);
    String deleteProduct(Long productId) throws ProductException;
    Product updateProduct(Long productId,Product product) throws ProductException;
    Product findProductById(Long productId) throws ProductException;
    public Page<Product> getAllProducts(String category,
                                       List<String>colors, List<String> sizes, Integer minPrice, Integer
                                               maxPrice, Integer minDiscount,String sort, String stock,
                                       Integer pageNumber, Integer pageSize);

    List<Product> findAllProducts();
}
