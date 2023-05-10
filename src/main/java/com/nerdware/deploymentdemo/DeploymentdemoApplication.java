package com.nerdware.deploymentdemo;


import com.github.javafaker.Faker;
import com.nerdware.deploymentdemo.Entity.Buyer;
import com.nerdware.deploymentdemo.Entity.Seller;
import com.nerdware.deploymentdemo.dao.BuyerRepository;
import com.nerdware.deploymentdemo.dao.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@EnableConfigurationProperties(JwtConfig.class)
public class DeploymentdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploymentdemoApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
//        return args -> {
//            Seller seller = new Seller();
//            seller.setName("linda");
//            seller.setPassword("password");
//
//            sellerRepository.save(seller);
//
//            Buyer buyer = new Buyer();
//
//            buyer.setFirstName("annasmith");
//            buyer.setPassword("password");
//
//            buyerRepository.save(buyer);
//
//        };
//    }


}
