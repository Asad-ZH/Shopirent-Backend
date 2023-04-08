package com.nerdware.deploymentdemo.controller;


import com.nerdware.deploymentdemo.Entity.Product;
import com.nerdware.deploymentdemo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody Product product, @RequestParam Long id) {
        productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }





}
