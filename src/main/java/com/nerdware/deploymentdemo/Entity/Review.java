package com.nerdware.deploymentdemo.Entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @SequenceGenerator(
            name = "review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "review_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "buyer_review_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "buyer_review_fk"
            )
    )
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(
            name = "product_review_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_review_fk"
            )
    )
    private Product product;

    private String review;
    private Integer rating;
}
