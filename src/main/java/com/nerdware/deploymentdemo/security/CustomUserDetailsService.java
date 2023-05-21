package com.nerdware.deploymentdemo.security;

import com.nerdware.deploymentdemo.models.Role;
import com.nerdware.deploymentdemo.models.Seller;
import com.nerdware.deploymentdemo.models.Buyer;
import com.nerdware.deploymentdemo.repository.SellerRepository;
import com.nerdware.deploymentdemo.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private SellerRepository sellerRepository;
    private BuyerRepository buyerRepository;

    @Autowired
    public CustomUserDetailsService(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (buyerRepository.existsByUsername(username)) {
            Buyer user2 = buyerRepository.findByUsername(username);
            return new User(user2.getUsername(), user2.getPassword(), mapRolesToAuthorities(user2.getRoles()));
        }

        else if(sellerRepository.existsByUsername(username)) {
            Seller user = sellerRepository.findByUsername(username);
            return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        }
        else {
            throw new UsernameNotFoundException("Not Registerd Yet");
        }
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
