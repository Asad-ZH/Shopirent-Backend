package com.nerdware.deploymentdemo.controller;


import com.nerdware.deploymentdemo.Entity.Seller;
import com.nerdware.deploymentdemo.service.SellerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/seller")
public class SellerController {

    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("{sellerId}")
    public Seller getSeller(@RequestParam Long sellerId) {
        return sellerService.getSeller(sellerId);
    }

    @GetMapping
    public List<Seller> getSellers() {
        return sellerService.getSellers();
    }

    @PostMapping
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerService.createSeller(seller);
    }

    @PutMapping("{sellerId}")
    public Seller updateSeller(@RequestBody Seller seller, @RequestParam Long sellerId) {
        return sellerService.updateSeller(seller, sellerId);
    }

    @DeleteMapping("{sellerId}")
    public void deleteSeller(@RequestParam Long sellerId) {
        sellerService.deleteSeller(sellerId);
    }




}
