package com.accenture.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO implements Serializable {
    private Long id;
    private ProductDTO product;
    private Integer quantity;
}
