package com.nerdware.deploymentdemo.auth;

import com.nerdware.deploymentdemo.dao.BuyerRepository;
import com.nerdware.deploymentdemo.dao.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

//    private final ApplicationUserDao applicationUserDao;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    @Autowired
    public ApplicationUserService(SellerRepository sellerRepository,
                                  BuyerRepository buyerRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       if(buyerRepository.findByUsername(username).isPresent()){
           System.out.println("Buyer found: " + username);
           return buyerRepository
                     .findByUsername(username).get();
         }else if(sellerRepository.findByUsername(username).isPresent()){
           System.out.println("Seller found: " + username);
           return sellerRepository
                     .findByUsername(username).get();
       }else {
           throw new UsernameNotFoundException(String.format("Username %s not found", username));
       }

//        return sellerRepository
//                .findByUsername(username)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException(String.format("Username %s not found", username))
//
//                );
    }
}
