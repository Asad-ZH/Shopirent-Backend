package com.nerdware.deploymentdemo.Entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "seller")
@Data
@Setter
@Getter
public class Seller implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "seller_sequence",
            sequenceName = "seller_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "seller_sequence"
    )
    private Long id;

    @OneToMany(
            mappedBy = "seller",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Product> products = new ArrayList<>();

    private String name;
    private String storeName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
    private String country;
    private String zipCode;

    private  String username;
    private  String password;

    @Lob
    @Column(name = "granted_authorities", columnDefinition = "text")
    private String grantedAuthorities;

    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  boolean isCredentialsNonExpired;
    private  boolean isEnabled;

    public Seller(){}
//    public Seller(String username,
//                           String password,
//                           Set<SimpleGrantedAuthority> grantedAuthorities,
//                           boolean isAccountNonExpired,
//                           boolean isAccountNonLocked,
//                           boolean isCredentialsNonExpired,
//                           boolean isEnabled) {
//        this.username = username;
//        this.password = password;
//        this.grantedAuthorities = grantedAuthorities;
//        this.isAccountNonExpired = isAccountNonExpired;
//        this.isAccountNonLocked = isAccountNonLocked;
//        this.isCredentialsNonExpired = isCredentialsNonExpired;
//        this.isEnabled = isEnabled;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (grantedAuthorities == null) {
            return Collections.emptySet();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("grantedAuthorities: " + grantedAuthorities);
            return mapper.readValue(grantedAuthorities, new TypeReference<Set<SimpleGrantedAuthority>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize granted authorities", e);
        }
    }
    public void setGrantedAuthorities(Set<SimpleGrantedAuthority> grantedAuthorities) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("grantedAuthorities: " + grantedAuthorities);
            this.grantedAuthorities = mapper.writeValueAsString(grantedAuthorities);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize granted authorities", e);
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
