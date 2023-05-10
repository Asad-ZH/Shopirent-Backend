package com.nerdware.deploymentdemo.controller;


import com.nerdware.deploymentdemo.Entity.Buyer;
import com.nerdware.deploymentdemo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/v1/buyer")
public class BuyerController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole( 'ROLE_BUYER')")
    public List<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_BUYER')")
    public Buyer getBuyerById(@RequestParam Long id) {
        return buyerService.getBuyerById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_BUYER')")
    public void addBuyer(@RequestBody Buyer buyer) {
        buyerService.addBuyer(buyer);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BUYER')")
    public void updateBuyer(@RequestBody Buyer buyer, @RequestParam Long id) {
        buyerService.updateBuyer(buyer, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BUYER')")
    public void deleteBuyer(@RequestParam Long id) {
        buyerService.deleteBuyer(id);
    }




}
