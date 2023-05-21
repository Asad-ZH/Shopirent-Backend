package com.nerdware.deploymentdemo.jwtcontrollers;


import com.nerdware.deploymentdemo.dto.AuthResponseDTO;
import com.nerdware.deploymentdemo.dto.LoginDto;
import com.nerdware.deploymentdemo.dto.RegisterBuyerDto;
import com.nerdware.deploymentdemo.dto.RegisterSellerDto;
import com.nerdware.deploymentdemo.models.Role;
import com.nerdware.deploymentdemo.models.Seller;
import com.nerdware.deploymentdemo.models.Buyer;
import com.nerdware.deploymentdemo.repository.RoleRepository;
import com.nerdware.deploymentdemo.repository.SellerRepository;
import com.nerdware.deploymentdemo.repository.BuyerRepository;
import com.nerdware.deploymentdemo.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthController {

    private AuthenticationManager authenticationManager;
    private SellerRepository sellerRepository;
    private BuyerRepository buyerRepository;

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          SellerRepository sellerRepository,
                          BuyerRepository buyerRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        Seller user = sellerRepository.findByUsername(loginDto.getUsername());

        if(user==null){
            Buyer user2 = buyerRepository.findByUsername(loginDto.getUsername());
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(token, loginDto.getUsername(), user2.getId(), "BUYER");
            return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
        }

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token, loginDto.getUsername(), user.getId(),"SELLER");

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);

    }

    @PostMapping("registerseller")
    public ResponseEntity<String> register(@RequestBody RegisterSellerDto registerSellerDto) {

        if (sellerRepository.existsByUsername(registerSellerDto.getUsername()) || buyerRepository.existsByUsername(registerSellerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Seller user = new Seller();
        user.setUsername(registerSellerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerSellerDto.getPassword())));
        user.setName(registerSellerDto.getName());
        user.setPhone(registerSellerDto.getPhone());
        user.setAddress(registerSellerDto.getAddress());
        user.setCity(registerSellerDto.getCity());
        user.setStoreName(registerSellerDto.getStoreName());
        user.setZipCode(registerSellerDto.getZipCode());


        Role roles = roleRepository.findByName("SELLER").get();
        user.setRoles(Collections.singletonList(roles));

        sellerRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("registerbuyer")
    public ResponseEntity<String> register2(@RequestBody RegisterBuyerDto registerBuyerDto) {

        if (buyerRepository.existsByUsername(registerBuyerDto.getUsername()) || sellerRepository.existsByUsername(registerBuyerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Buyer user2 = new Buyer();
        user2.setUsername(registerBuyerDto.getUsername());
        user2.setPassword(passwordEncoder.encode((registerBuyerDto.getPassword())));

        Role roles = roleRepository.findByName("BUYER").get();
        user2.setRoles(Collections.singletonList(roles));

        buyerRepository.save(user2);

        return new ResponseEntity<>("User2 registered success!", HttpStatus.OK);
    }
    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            JwtTokenBlacklist.add(token);
        }

        return new ResponseEntity<>("logout successfully!", HttpStatus.OK);
    }

}
