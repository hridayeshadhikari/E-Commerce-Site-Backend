package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface
ProductService {
    Product createProduct(CreateProductRequest request);
    String deleteProduct(Long productId);
    Product updateProduct(Long productId,Product product);
    Product findProductById(Long productId);
    List<Product> findProductByCategory(String category);
    Page<Product> getAllProduct(String category,List<String> color,List<String> size,
                                Integer minPrice,Integer maxPrice,Integer minDiscount,Integer maxDiscount,
                                String stock,String sort,Integer pageNumber,Integer pageSize);


}
