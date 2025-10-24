package com.accenture.product.service;

import com.accenture.product.config.OrderResponseMsgConfig;
import com.accenture.product.dto.InventoryDTO;
import com.accenture.product.dto.OrderDTO;
import com.accenture.product.dto.ProductDTO;
import com.accenture.product.entity.ProductOrder;
import com.accenture.product.jpa.OrderRepository;
import com.accenture.product.util.OrderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderUtil oderUtil;

    @Autowired
    private OrderRemoteService orderRemoteService;

    @Autowired
    private OrderResponseMsgConfig orderResMsg;

    public OrderDTO placeOrder(String name, Integer qty) {
        ProductDTO product = findProductByName(name);
        inventoryAvailable(product, qty);
        ProductOrder order = orderRepository.save(ProductOrder.builder().productId(product.getCode()).quantity(qty).totalCost(product.getPrice() * qty).build());
        return oderUtil.convertDTO(order, product);
    }


    private ProductDTO findProductByName(String name) {
        ProductDTO productDTO = orderRemoteService.invokeGetProductService(name);
        return productDTO;
    }

    private void inventoryAvailable(ProductDTO product, Integer qty) {
        InventoryDTO inventory = orderRemoteService.invokeGetInventoryService(product.getCode());
        if (inventory.getQuantity() < qty) {
            throw new RuntimeException(orderResMsg.stocksNotAvailable(qty, inventory.getQuantity()));
        } else if ((inventory.getQuantity() - qty) == 0) {
            String prodDeletedMessage = orderRemoteService.invokeDeleteProductService(product.getCode());
            String invDeletedMessage = orderRemoteService.invokeDeleteInventoryService(inventory.getId());
            LOG.info("{} \n {}", prodDeletedMessage, invDeletedMessage);
        } else {
            orderRemoteService.invokeUpdateInventoryService(inventory.getId(), qty);
        }

    }
}
