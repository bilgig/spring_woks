package com.salessite.management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_quantity")
    private Integer productQuantity;

    @Column(name="price")
    private Double price;

    @Column(name="status")
    private boolean status;
}
