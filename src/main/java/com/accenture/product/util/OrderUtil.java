package com.accenture.product.util;

import com.accenture.product.dto.OrderDTO;
import com.accenture.product.dto.ProductDTO;
import com.accenture.product.entity.ProductOrder;
import org.springframework.stereotype.Component;

@Component
public class OrderUtil {

    public OrderDTO convertDTO(ProductOrder order, ProductDTO product) {
        return OrderDTO.builder().code(order.getCode()).product(product).quantity(order.getQuantity()).totalCost(order.getTotalCost()).build();
    }
}
