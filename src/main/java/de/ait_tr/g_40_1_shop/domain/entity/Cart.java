package de.ait_tr.g_40_1_shop.domain.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
private Customer customer;

     @Column(name = "product")
    private List<Product> product;


}
