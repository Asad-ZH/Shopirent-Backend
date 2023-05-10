package com.nerdware.deploymentdemo;


import com.nerdware.deploymentdemo.Entity.Buyer;
import com.nerdware.deploymentdemo.Entity.Seller;
import com.nerdware.deploymentdemo.dao.BuyerRepository;
import com.nerdware.deploymentdemo.dao.SellerRepository;
import com.nerdware.deploymentdemo.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static com.nerdware.deploymentdemo.security.ApplicationUserRole.BUYER;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class DeploymentdemoApplication {
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BuyerRepository buyerRepository;

    public static void main(String[] args) {
        SpringApplication.run(DeploymentdemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SellerRepository sellerRepository, BuyerRepository buyerRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));

            Seller seller = new Seller();
            seller.setUsername("linda");
            seller.setPassword(passwordEncoder.encode("password"));
            seller.setGrantedAuthorities(authorities);
            seller.setAccountNonExpired(true);
            seller.setAccountNonLocked(true);
            seller.setCredentialsNonExpired(true);
            seller.setEnabled(true);
            sellerRepository.save(seller);

            System.out.println(seller.getGrantedAuthorities());

            Set<SimpleGrantedAuthority> authorities2 = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_BUYER"));

            Buyer buyer = new Buyer();
            buyer.setUsername("linda2");
            buyer.setPassword(passwordEncoder.encode("password"));
            buyer.setGrantedAuthorities(authorities);
            buyer.setAccountNonExpired(true);
            buyer.setAccountNonLocked(true);
            buyer.setCredentialsNonExpired(true);
            buyer.setEnabled(true);
            buyerRepository.save(buyer);
            System.out.println(buyer.getGrantedAuthorities());
            //            Buyer buyer = new Buyer(
//                    "annasmith",
//                    passwordEncoder.encode("password"),
//                    authorities,
//                    true,
//                    true,
//                    true,
//                    true
//            );


//            buyer.setFirstName("annasmith");
//            buyer.setLastName("annasmith");
//            buyer.setEmail("anna@gmail.com");
//            buyer.setPhone(1234567890);

//            System.out.println("hoaaaaaaalaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//            System.out.println(buyer.getUsername());
//
//            buyerRepository.save(buyer);

//            buyerRepository.save(((Buyer) buyer));

//            Buyer buyer = new Buyer(
//                    "linda",
//                    passwordEncoder.encode("password"),
//                    BUYER.getGrantedAuthorities(),
//                    true,
//                    true,
//                    true,
//                    true
//            );
//
//            buyer.setFirstName("annasmith");
//            buyer.setPassword("password");
//
//            buyerRepository.save(buyer);

        };
    }


}
