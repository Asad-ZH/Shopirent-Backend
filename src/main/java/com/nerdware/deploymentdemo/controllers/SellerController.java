package com.nerdware.deploymentdemo.controllers;


import com.nerdware.deploymentdemo.models.Seller;
import com.nerdware.deploymentdemo.service.SellerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/seller")
public class SellerController {

    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("{sellerId}")
    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    public Seller getSeller(@RequestParam Long sellerId) {
        return sellerService.getSeller(sellerId);
    }

    @PutMapping("{sellerId}")
    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    public Seller updateSeller(@RequestBody Seller seller, @RequestParam Long sellerId) {
        return sellerService.updateSeller(seller, sellerId);
    }
}
