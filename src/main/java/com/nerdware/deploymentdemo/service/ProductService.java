package com.nerdware.deploymentdemo.service;


import com.nerdware.deploymentdemo.models.Product;
import com.nerdware.deploymentdemo.models.Seller;
import com.nerdware.deploymentdemo.repository.BuyerRepository;
import com.nerdware.deploymentdemo.repository.ProductRepository;
import com.nerdware.deploymentdemo.repository.SellerRepository;
import com.nerdware.deploymentdemo.security.JWTAuthenticationFilter;
import com.nerdware.deploymentdemo.security.JWTGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final HttpServletRequest request;

    JWTAuthenticationFilter jwtAuthenticationFilter;
    JWTGenerator jwtGenerator;

    BuyerRepository buyerRepository;
    ProductRepository productRepository;
    SellerRepository sellerRepository;

    @Autowired
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

    public List<Product> getAllSellerProducts() {
        Seller seller = extractSellerIdFromToken();
        return seller.getProducts();
    }

    public void updateProduct(Product updatedProduct, Long id) throws Exception{
        Seller seller = extractSellerIdFromToken();

        // Retrieve the existing product from the database
        Product existingProduct = productRepository.findById(id).orElseThrow();

        // Check if the product exists
        if (existingProduct == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        if (!(existingProduct.getSeller().getId() == seller.getId())){
            throw new IllegalStateException("Seller is not authenticated");
        }
        System.out.println("product seller id 1"+existingProduct.getSeller().getId());
        System.out.println(seller.getId());

        // Copy the properties from the updatedProduct to the existingProduct
        long temp = existingProduct.getId();
        BeanUtils.copyProperties(updatedProduct, existingProduct);
        existingProduct.setId(temp);

        productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Seller seller = extractSellerIdFromToken();

        Product existingProduct = productRepository.findById(id).orElseThrow();

        if (existingProduct == null) {
            throw new IllegalStateException("Product not found");

        }
        if (!(existingProduct.getSeller().getId() == seller.getId())){
            throw new IllegalStateException("Seller is not authenticated");
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
