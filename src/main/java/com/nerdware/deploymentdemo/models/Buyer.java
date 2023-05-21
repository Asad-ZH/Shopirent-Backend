package com.nerdware.deploymentdemo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buyer")
@Data
@NoArgsConstructor
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToOne(
//            mappedBy = "buyer",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private Favourites favourites;
//
//    @OneToMany(
//            mappedBy = "buyer",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
//    )
//    private List<OrderDetails> orderDetails;
//
//    @OneToMany(
//            mappedBy = "buyer",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
//    )
//    private List<Review> BuyerReviews;


    private String username;
    private String password;

    private String name;
    private Integer phone;
    private String address;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "buyer_roles", joinColumns = @JoinColumn(name = "buyer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

}
