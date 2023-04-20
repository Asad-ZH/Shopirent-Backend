package com.nerdware.deploymentdemo.Entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetails {

    @Id
    @SequenceGenerator(
            name = "order_details_sequence",
            sequenceName = "order_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "order_details_sequence"
    )
    private Long Id;

    @ManyToOne
    @JoinColumn(
            name = "order_details_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "buyer_order_details_fk"
            )
    )
    private Buyer buyer;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "product_order_details_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_order_details_id_fk"
            )
    )
    private Product product;

    private Integer quantity;
    private String paymentType;
    private Integer phoneNumber;
    private double price;

}
