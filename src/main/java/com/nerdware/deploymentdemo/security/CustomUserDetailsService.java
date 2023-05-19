package com.nerdware.deploymentdemo.security;

import com.nerdware.deploymentdemo.models.Role;
import com.nerdware.deploymentdemo.models.UserEntity;
import com.nerdware.deploymentdemo.models.UserEntity2;
import com.nerdware.deploymentdemo.repository.UserRepository;
import com.nerdware.deploymentdemo.repository.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private UserRepository userRepository;
    private UserRepository2 userRepository2;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserRepository2 userRepository2) {
        this.userRepository = userRepository;
        this.userRepository2 = userRepository2;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (userRepository2.existsByUsername(username)) {
            UserEntity2 user2 = userRepository2.findByUsername(username);
            return new User(user2.getUsername(), user2.getPassword(), mapRolesToAuthorities(user2.getRoles()));
        }

        else if(userRepository.existsByUsername(username)) {
            UserEntity user = userRepository.findByUsername(username);
            return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        }
        else {
            throw new UsernameNotFoundException("Not Registerd Yet");
        }
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
