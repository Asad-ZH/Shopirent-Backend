package com.nerdware.deploymentdemo.controllers;


import com.nerdware.deploymentdemo.dto.AuthResponseDTO;
import com.nerdware.deploymentdemo.dto.LoginDto;
import com.nerdware.deploymentdemo.dto.RegisterBuyerDto;
import com.nerdware.deploymentdemo.dto.RegisterSellerDto;
import com.nerdware.deploymentdemo.models.Role;
import com.nerdware.deploymentdemo.models.UserEntity;
import com.nerdware.deploymentdemo.models.UserEntity2;
import com.nerdware.deploymentdemo.repository.RoleRepository;
import com.nerdware.deploymentdemo.repository.UserRepository;
import com.nerdware.deploymentdemo.repository.UserRepository2;
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
    private UserRepository userRepository;
    private UserRepository2 userRepository2;

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          UserRepository2 userRepository2,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userRepository2 = userRepository2;
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

        UserEntity user = userRepository.findByUsername(loginDto.getUsername());

        if(user==null){
            UserEntity2 user2 = userRepository2.findByUsername(loginDto.getUsername());
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(token, loginDto.getUsername(), user2.getId(), "BUYER");
            return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
        }

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token, loginDto.getUsername(), user.getId(),"SELLER");

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);

    }

    @PostMapping("registerseller")
    public ResponseEntity<String> register(@RequestBody RegisterSellerDto registerSellerDto) {

        if (userRepository.existsByUsername(registerSellerDto.getUsername()) || userRepository2.existsByUsername(registerSellerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
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

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("registerbuyer")
    public ResponseEntity<String> register2(@RequestBody RegisterBuyerDto registerBuyerDto) {

        if (userRepository2.existsByUsername(registerBuyerDto.getUsername()) || userRepository.existsByUsername(registerBuyerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity2 user2 = new UserEntity2();
        user2.setUsername(registerBuyerDto.getUsername());
        user2.setPassword(passwordEncoder.encode((registerBuyerDto.getPassword())));

        Role roles = roleRepository.findByName("BUYER").get();
        user2.setRoles(Collections.singletonList(roles));

        userRepository2.save(user2);

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
