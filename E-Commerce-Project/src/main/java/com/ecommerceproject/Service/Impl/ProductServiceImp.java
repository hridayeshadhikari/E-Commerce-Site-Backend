package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Category;
import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Repository.CategoryRepository;
import com.ecommerceproject.Repository.ProductRepository;
import com.ecommerceproject.Request.CreateProductRequest;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Override
    public Product createProduct(CreateProductRequest request) {
        String categoryName = request.getCategory();
        Category category = categoryRepository.findByName(categoryName);

        // If category doesn't exist, create and save it
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            category = categoryRepository.save(category);
        }

        Product product = new Product();
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
        product.setTimeStamp(LocalDateTime.now());

        // Associate the product with the category
        product.setCategory(category);

        return productRepository.save(product);
    }


    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty()){
            throw new ProductException("no product found with this id "+productId);
        }
        productRepository.deleteById(productId);
        return "deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        Product product1=findProductById(productId);
        if(product1!=null){
            product1.setTitle(product.getTitle());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            product1.setDiscountedPrice(product.getDiscountedPrice());
            product1.setDiscountPercent(product.getDiscountPercent());
            product1.setBrand(product.getBrand());
            product1.setColor(product.getColor());
            product1.setQuantity(product.getQuantity());
            product1.setImageUrl(product.getImageUrl());
            product1.setSize(product.getSize());
            product1.setTimeStamp(LocalDateTime.now());
        }

        return productRepository.save(product1);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty()){
            throw new ProductException("no product found with this id "+productId);
        }
        return product.get();
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> products=productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> color,
                                       List<String> size, Integer minPrice, Integer maxPrice,
                                       Integer minDiscount, Integer maxDiscount, String stock,
                                       String sort, Integer pageNumber, Integer pageSize) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        List<Product> products=productRepository.filterProduct(category,minPrice,minDiscount,maxDiscount,maxPrice,sort);
        if(!color.isEmpty()){
            products=products.stream().filter(p-> color.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());

        }
        if(stock!=null) {
            if (stock.equals("In_Stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0)
                        .collect(Collectors.toList());
            } else if (stock.equals("Out_Of_Stock")) {
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }

        }
        
        return null;
    }
}
