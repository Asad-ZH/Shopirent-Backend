package com.nerdware.deploymentdemo.controllers;


import com.nerdware.deploymentdemo.models.Buyer;
import com.nerdware.deploymentdemo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/buyer")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BuyerController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Seller', 'Buyer')")
    public Buyer getBuyerById(@RequestParam Long id) {
        return buyerService.getBuyerById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Buyer')")
    public void updateBuyer(@RequestBody Buyer buyer, @RequestParam Long id) {
        buyerService.updateBuyer(buyer, id);
    }

}
