package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProductByCategory(@RequestParam String category,@RequestParam List<String> color,@RequestParam List<String> size,
                                                              @RequestParam Integer minPrice,@RequestParam Integer maxPrice,@RequestParam Integer minDiscount,@RequestParam Integer maxDiscount,
                                                              @RequestParam String stock,@RequestParam String sort,@RequestParam Integer pageNumber,@RequestParam Integer pageSize){

        Page<Product> response=productService.findProductByCategory(category,color,size,minPrice,maxPrice,minDiscount,maxDiscount,stock,sort,pageNumber,pageSize);

        System.out.println("Products");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) throws ProductException {
        Product product=productService.findProductById(id);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

}
