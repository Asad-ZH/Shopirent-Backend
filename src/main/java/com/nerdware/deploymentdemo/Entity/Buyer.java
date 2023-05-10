package com.nerdware.deploymentdemo.Entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "buyer")
@Setter
@Getter
@NoArgsConstructor
public class Buyer implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "favourites_sequence",
            sequenceName = "favourites_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "favourites_sequence"
    )
    private Long id;
    @OneToOne(
            mappedBy = "buyer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Favourites favourites;

    @OneToMany(
            mappedBy = "buyer",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<OrderDetails> orderDetails;

    @OneToMany(
            mappedBy = "buyer",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Review> BuyerReviews;

    private String firstName;
    private String lastName;
    private String email;
    private Integer phone;

    private String username;
    private String password;

    @Lob
    @Column(name = "granted_authorities", columnDefinition = "text")
    private String grantedAuthorities;

    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  boolean isCredentialsNonExpired;
    private  boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (grantedAuthorities == null) {
            return Collections.emptySet();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(grantedAuthorities, new TypeReference<Set<SimpleGrantedAuthority>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize granted authorities", e);
        }
    }

    public void setGrantedAuthorities(Set<SimpleGrantedAuthority> grantedAuthorities) {
        ObjectMapper mapper = new ObjectMapper();
        try {
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
