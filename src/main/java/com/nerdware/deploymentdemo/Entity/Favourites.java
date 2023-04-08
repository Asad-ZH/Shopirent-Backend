package com.nerdware.deploymentdemo.Entity;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Table(appliesTo = "favourites")
public class Favourites {

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
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "buyer_favourites_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "buyer_favourites_id_fk"
            )
    )
    private Buyer buyer;

    @OneToMany(
            mappedBy = "favourites",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Product> products = new ArrayList<>();
}
