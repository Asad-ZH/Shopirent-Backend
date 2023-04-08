package com.nerdware.deploymentdemo.service;


import com.nerdware.deploymentdemo.Entity.Product;
import com.nerdware.deploymentdemo.dao.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product with id " + id + " does not exist"));
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product, Long id) {
        productRepository.findById(product.getId())
                .ifPresent(product1 -> {
                    productRepository.save(product);
                });
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Product with id " + id + " does not exist");
        }
        productRepository.deleteById(id);
    }
}
