package com.nerdware.deploymentdemo.security;

import com.nerdware.deploymentdemo.Entity.Buyer;
import com.nerdware.deploymentdemo.Entity.Seller;
import com.nerdware.deploymentdemo.dao.BuyerRepository;
import com.nerdware.deploymentdemo.dao.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @PostMapping("/buyer")
    public ResponseEntity<?> registerBuyer(@RequestBody Buyer buyer) {

        if (buyerRepository.findByEmail(buyer.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Error: Buyer Email is already taken!");
        }

        Buyer newBuyer = new Buyer();
        newBuyer.setEmail(buyer.getEmail());
        newBuyer.setPassword(passwordEncoder.encode(buyer.getPassword()));
        newBuyer.setName(buyer.getName());
        newBuyer.setAddress(buyer.getAddress());

        buyerRepository.save(newBuyer);

        return ResponseEntity.ok("Buyer registered successfully");
    }

    @PostMapping("/seller")
    public ResponseEntity<?> registerSeller(@RequestBody Seller seller) {

        if (sellerRepository.findByEmail(seller.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Error: Seller Email is already taken!");
        }

        Seller newSeller = new Seller();

        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setName(seller.getName());
        newSeller.setAddress(seller.getAddress());
        newSeller.setStoreName(seller.getStoreName());
        newSeller.setCity(seller.getCity());
        newSeller.setCountry(seller.getCountry());
        newSeller.setZipCode(seller.getZipCode());
        newSeller.setPhone(seller.getPhone());

        sellerRepository.save(newSeller);

        return ResponseEntity.ok("Seller registered successfully");
    }
}
