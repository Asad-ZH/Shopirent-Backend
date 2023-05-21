package com.nerdware.deploymentdemo.controllers;


import com.nerdware.deploymentdemo.models.Product;
import com.nerdware.deploymentdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Buyer','Seller')")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/sellerproducts")
    @PreAuthorize("hasAnyAuthority('Buyer','Seller')")
    public List<Product> getAllSellerProducts() {
        return productService.getAllSellerProducts();
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('Seller')")
    public void addProduct(@RequestBody Product product) {
        System.out.println("Product: " + product);
        productService.addProductToSeller(product);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Seller')")
    public void updateProduct(@RequestBody Product product, @RequestParam Long id) {
        productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Seller')")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }

}
