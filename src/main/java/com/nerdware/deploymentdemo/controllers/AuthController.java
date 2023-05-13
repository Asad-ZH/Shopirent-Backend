package com.nerdware.deploymentdemo.controllers;


import com.nerdware.deploymentdemo.dto.AuthResponseDTO;
import com.nerdware.deploymentdemo.dto.LoginDto;
import com.nerdware.deploymentdemo.dto.RegisterDto;
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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
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
        return new ResponseEntity<>(new AuthResponseDTO(token, loginDto.getUsername()), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("SELLER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    @PostMapping("register2")
    public ResponseEntity<String> register2(@RequestBody RegisterDto registerDto) {

        if (userRepository2.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity2 user2 = new UserEntity2();
        user2.setUsername(registerDto.getUsername());
        user2.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("BUYER").get();
        user2.setRoles(Collections.singletonList(roles));

        userRepository2.save(user2);

        return new ResponseEntity<>("User2 registered success!", HttpStatus.OK);
    }
    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Retrieve the JWT token from the Authorization header
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            System.out.println("logout1 successfullylogout1 successfullylogout1 successfullylogout1 successfullylogout1 successfully");

            String token = header.substring(7);
            // Invalidate the JWT token by adding it to a blacklist
            JwtTokenBlacklist.add(token);
        }

        System.out.println("logout successfully");
        // Return a response indicating that the user has been logged out
        return new ResponseEntity<>("logout successfully!", HttpStatus.OK);
    }

}
