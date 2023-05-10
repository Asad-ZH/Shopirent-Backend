package com.nerdware.deploymentdemo.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Table(name = "buyer")
public class Buyer {

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

    private String name;
    private String email;
    private String password;
    private Integer phone;
    private String address;


}
