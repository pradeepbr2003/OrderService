package com.accenture.product.service;

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

import static com.accenture.product.enums.UrlEnum.*;

@Service
public class OrderService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderUtil oderUtil;

    @Autowired
    private OrderRemoteService orderRemoteService;

    public OrderDTO placeOrder(String name, Integer qty) {
        ProductDTO product = findProductByName(name);
        inventoryAvailable(product, qty);
        ProductOrder order = orderRepository.save(ProductOrder.builder().productId(product.getCode()).quantity(qty).totalCost(product.getPrice() * qty).build());
        return oderUtil.convertDTO(order, product);
    }


    private ProductDTO findProductByName(String name) {
        String productFindUrl = String.format(PROD_FORMAT_URL.value(), PRODUCT_FIND_URL.value(), name);
        ProductDTO productDTO = orderRemoteService.invokeGetProductService(productFindUrl);
        return productDTO;
    }

    private void inventoryAvailable(ProductDTO product, Integer qty) {
        String inventoryFindUrl = String.format(INV_FIND_FORMAT_URL.value(), INVENTORY_FIND_URL.value(), product.getCode());
        InventoryDTO inventory = orderRemoteService.invokeGetInventoryService(inventoryFindUrl);
        if (inventory.getQuantity() < qty) {
            throw new RuntimeException(String.format("No Stock , Expected quantity : %d , but available only : %d", qty, inventory.getQuantity()));
        } else if ((inventory.getQuantity() - qty) == 0) {
            String inventoryUrl = String.format(INV_DELETE_FORMAT_URL.value(), INVENTORY_URL.value(), inventory.getId());
            String message = orderRemoteService.invokeDeleteInventoryService(inventoryUrl);
            LOG.info(message);
        } else {
            String inventoryUrl = String.format(INV_FORMAT_URL.value(), INVENTORY_URL.value(), inventory.getId(), qty);
            orderRemoteService.invokeUpdateInventoryService(inventoryUrl);
        }

    }
}
