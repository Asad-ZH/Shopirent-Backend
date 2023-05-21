package com.nerdware.deploymentdemo.service;


import com.nerdware.deploymentdemo.models.Buyer;
import com.nerdware.deploymentdemo.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;

    @Autowired

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Buyer getBuyerById(Long id) {
        return buyerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Buyer with id " + id + " does not exist"));
    }

    public void addBuyer(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public void updateBuyer(Buyer buyer, Long id) {
        buyerRepository.findById(buyer.getId())
                .ifPresent(user1 -> {
                    buyerRepository.save(buyer);
                });
    }

    public void deleteBuyer(Long id) {
        boolean exists = buyerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Buyer with id " + id + " does not exist");
        }
        buyerRepository.deleteById(id);
    }
}
