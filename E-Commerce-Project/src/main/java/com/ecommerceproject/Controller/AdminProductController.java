package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Request.CreateProductRequest;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
@AllArgsConstructor
public class AdminProductController {


    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest productRequest){
        Product product1=productService.createProduct(productRequest);
        return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception {
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductException {
        Product product=productService.findProductById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product) throws ProductException {
        //Product product1=productService.findProductById(productId);
        Product product1=productService.updateProduct(productId,product);
        return new  ResponseEntity<Product>(product1,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products=productService.findAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
