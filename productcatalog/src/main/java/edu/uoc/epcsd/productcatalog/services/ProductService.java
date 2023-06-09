package edu.uoc.epcsd.productcatalog.services;

import edu.uoc.epcsd.productcatalog.entities.Category;
import edu.uoc.epcsd.productcatalog.entities.Product;
import edu.uoc.epcsd.productcatalog.repositories.ProductRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(Long categoryId, String name, String description, Double dailyPrice, String brand, String model) {

        Product product = Product.builder().name(name).description(description).dailyPrice(dailyPrice).brand(brand).model(model).build();

        if (categoryId != null) {
            Optional<Category> category = categoryService.findById(categoryId);

            category.ifPresent(product::setCategory);//if category is present
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByCategory(String categoryName) {
        Optional<Category> category = categoryService.findByName(categoryName);

        if (category.isPresent()) {
            return productRepository.findByCategory(category.get());
        } else {
            return new ArrayList<>();
        }
    }


}
