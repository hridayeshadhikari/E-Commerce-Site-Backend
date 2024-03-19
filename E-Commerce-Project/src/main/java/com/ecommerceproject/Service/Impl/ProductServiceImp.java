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
import org.springframework.data.domain.PageImpl;
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

        Category firstLevel = categoryRepository.findByName(request.getFirstLevelCategory());


        if(firstLevel==null){
            Category firstLevelCategory=new Category();
            firstLevelCategory.setName(request.getFirstLevelCategory());
            firstLevelCategory.setLevel(1);
            firstLevel=categoryRepository.save(firstLevelCategory);
        }
        Category secondLevel=categoryRepository.findByNameAndParent(request.getSecondLevelCategory(),firstLevel.getName());
        if(secondLevel==null){
            Category secondLevelCategory=new Category();
            secondLevelCategory.setParentCategory(firstLevel);
            secondLevelCategory.setName(request.getSecondLevelCategory());
            secondLevelCategory.setLevel(2);
            secondLevel=categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel=categoryRepository.findByNameAndParent(request.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel==null){
            Category thirdLevelCategory=new Category();
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setName(request.getThirdLevelCategory());
            thirdLevelCategory.setLevel(3);
            thirdLevel=categoryRepository.save(thirdLevelCategory);
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
        product.setExtraImageUrl(request.getExtraImageUrl());
        product.setHighlights(request.getHighlights());
        product.setSize(request.getSize());
        product.setTimeStamp(LocalDateTime.now());

        // Associate the product with the category
        product.setCategory(thirdLevel);

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
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product1=findProductById(productId);
        if(req.getQuantity()!=0){
            product1.setQuantity(req.getQuantity());
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
    public Page<Product> getAllProducts(String category, List<String>colors,
                                       List<String> sizes, Integer minPrice, Integer maxPrice,
                                       Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize ) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProduct(category, minPrice, maxPrice, minDiscount, sort);


        if (!colors.isEmpty()) {
            products = products.stream()
                    .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());


        }

        if(stock!=null) {

            if(stock.equals("in_stock")) {
                products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }
            else if (stock.equals("out_of_stock")) {
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }


        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
        return filteredProducts; // If color list is empty, do nothing and return all products


    }


    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
