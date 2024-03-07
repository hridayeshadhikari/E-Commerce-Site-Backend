package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> filterProduct(@RequestParam String category,
                                                                      @RequestParam List<String>color,@RequestParam List<String> size,@RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort,
                                                                      @RequestParam String stock, @RequestParam Integer pageNumber,@RequestParam Integer pageSize){


        Page<Product> res= productService.getAllProducts(category, color, size, minPrice, maxPrice, minDiscount, sort,stock,pageNumber,pageSize);

        System.out.println("complete products");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) throws ProductException {
        Product product=productService.findProductById(id);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

}
