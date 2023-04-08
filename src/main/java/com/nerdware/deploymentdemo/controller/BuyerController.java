package com.nerdware.deploymentdemo.controller;


import com.nerdware.deploymentdemo.Entity.Buyer;
import com.nerdware.deploymentdemo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }
    @GetMapping("/{id}")
    public Buyer getBuyerById(@RequestParam Long id) {
        return buyerService.getBuyerById(id);
    }

    @PostMapping()
    public void addBuyer(@RequestBody Buyer buyer) {
        buyerService.addBuyer(buyer);
    }

    @PutMapping("/{id}")
    public void updateBuyer(@RequestBody Buyer buyer, @RequestParam Long id) {
        buyerService.updateBuyer(buyer, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBuyer(@RequestParam Long id) {
        buyerService.deleteBuyer(id);
    }




}
