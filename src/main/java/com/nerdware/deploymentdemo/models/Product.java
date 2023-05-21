package com.nerdware.deploymentdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "product")
@Data
public class Product {

    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "product_sequence"
    )
    @Id
    private Long Id;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "seller_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "seller_product_fk"
            )
    )
    private Seller seller;

//    @OneToMany(
//            mappedBy = "product",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//    @Nullable
//    private List<Review> productReviews;
//
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(
//            name = "favourites_product_id",
//            nullable = false,
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "favourites_product_id"
//            )
//    )
//    private Favourites favourites;
//
//    @Nullable
//    @OneToOne(
//            mappedBy = "product",
//            orphanRemoval = true,
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
//    )
//    private OrderDetails orderDetails;

    private String title;
    private String description;
    private String imageURL;
    private String category;
    private String subCategory;
    private Integer securityDeposit;
    private double rentingPrice;
    private double sellingPrice;
    private double saleRentingPrice;
    private double saleSellingPrice;
    private String size;
    private String highlights;

}
