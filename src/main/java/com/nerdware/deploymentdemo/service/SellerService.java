package com.nerdware.deploymentdemo.service;


import com.nerdware.deploymentdemo.Entity.Seller;
import com.nerdware.deploymentdemo.dao.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller getSeller(Long sellerId) {
        return sellerRepository.getById(sellerId);
    }

    public List<Seller> getSellers() {
        return sellerRepository.findAll();
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Seller seller, Long sellerId) {
        boolean exists = sellerRepository.existsById(sellerId);
        if (!exists) {
            throw new IllegalStateException("Seller with id " + sellerId + " does not exist");
        }
        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long sellerId) {
        boolean exists = sellerRepository.existsById(sellerId);
        if (!exists) {
            throw new IllegalStateException("Seller with id " + sellerId + " does not exist");
        }
        sellerRepository.deleteById(sellerId);
    }
}
