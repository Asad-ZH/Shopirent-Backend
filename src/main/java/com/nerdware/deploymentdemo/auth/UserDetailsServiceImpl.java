package com.nerdware.deploymentdemo.auth;

import com.nerdware.deploymentdemo.Entity.Buyer;
import com.nerdware.deploymentdemo.Entity.Seller;
import com.nerdware.deploymentdemo.dao.BuyerRepository;
import com.nerdware.deploymentdemo.dao.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Buyer buyer = buyerRepository.findByEmail(email);
        if (buyer != null) {
            return new User(buyer.getEmail(), buyer.getPassword(), new ArrayList<>());
        }

        Seller seller = sellerRepository.findByEmail(email);
        if (seller != null) {
            return new User(seller.getEmail(), seller.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
