package com.java.services;

import com.java.models.entities.Product;
import com.java.models.repos.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Save (create)
    public Product save(Product product) {
        return productRepo.save(product);
    }

    // Update
    public Product update(Long id, Product productDetails) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            return productRepo.save(product);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

    // Delete
    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return (List<Product>) productRepo.findAll();
    }

    // Find by ID
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    // Find by name (contains)
    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }
}
