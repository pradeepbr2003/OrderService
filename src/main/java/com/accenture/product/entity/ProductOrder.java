package com.accenture.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String code;

    @Column
    private String productId;

    @Column
    private int quantity;

    @Column
    private double totalCost;
}
