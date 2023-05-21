package com.nerdware.deploymentdemo.service;


import com.nerdware.deploymentdemo.models.Product;
import com.nerdware.deploymentdemo.models.Seller;
import com.nerdware.deploymentdemo.repository.BuyerRepository;
import com.nerdware.deploymentdemo.repository.ProductRepository;
import com.nerdware.deploymentdemo.repository.SellerRepository;
import com.nerdware.deploymentdemo.security.JWTAuthenticationFilter;
import com.nerdware.deploymentdemo.security.JWTGenerator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProductService {

    private final HttpServletRequest request;

    JWTAuthenticationFilter jwtAuthenticationFilter;
    JWTGenerator jwtGenerator;

    BuyerRepository buyerRepository;
    ProductRepository productRepository;
    SellerRepository sellerRepository;



    public ProductService(HttpServletRequest request, JWTAuthenticationFilter jwtAuthenticationFilter, JWTGenerator jwtGenerator, BuyerRepository buyerRepository, ProductRepository productRepository, SellerRepository sellerRepository) {
        this.request = request;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtGenerator = jwtGenerator;
        this.buyerRepository = buyerRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product with id " + id + " does not exist"));
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

    public void addProductToSeller(Product product) {
        Seller seller = extractSellerIdFromToken();
            product.setSeller(seller);
            productRepository.save(product);
            seller.getProducts().add(product);
            sellerRepository.save(seller);

    }

    private Seller extractSellerIdFromToken() {
        String bearerToken = request.getHeader("Authorization");
        bearerToken = bearerToken.substring(7, bearerToken.length());
        String username = jwtGenerator.getUsernameFromJWT(bearerToken);
        Seller seller = sellerRepository.findByUsername(username);
        return seller;

    }


}
