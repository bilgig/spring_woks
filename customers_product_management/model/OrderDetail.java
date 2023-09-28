package com.salessite.management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter //lombok
@Setter //lombok
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "product_id")
    private Long productId;

}