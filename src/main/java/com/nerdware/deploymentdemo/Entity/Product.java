package com.nerdware.deploymentdemo.Entity;

import lombok.Data;
import org.hibernate.annotations.Type;

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

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "seller_product_fk"
            )
    )
    private Seller seller;

    @OneToMany(
            mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Review> productReviews;

    @ManyToOne
    @JoinColumn(
            name = "favourites_product_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "favourites_product_id"
            )
    )
    private Favourites favourites;

    @OneToOne(
            mappedBy = "product",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private OrderDetails orderDetails;

    private String title;
    private String description;
    private String imageURL;
    private String category;
    private String subCategory;
    private String weather;
    private String sleeves;
    private String brand;
    private String bodyType;
    private Integer securityDeposit;
    private double rentingPrice;
    private double sellingPrice;
    private double saleRentingPrice;
    private double saleSellingPrice;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private List<String> colors;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private List<String> size;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private List<String> highlights;


    public Product() {

    }
}
