package com.nerdware.deploymentdemo.Entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
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

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer phone;

    public Buyer() {
    }

    public Buyer(String firstName, String lastName, String email, String password, Integer phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<Review> getBuyerReviews() {
        return BuyerReviews;
    }

    public void setBuyerReviews(List<Review> buyerReviews) {
        BuyerReviews = buyerReviews;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
