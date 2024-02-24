package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Request.CreateProductRequest;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest productRequest){
        Product product1=productService.createProduct(productRequest);
        return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
    }
}
