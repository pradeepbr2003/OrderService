package com.accenture.product.service;

import com.accenture.product.config.OrderServiceUrl;
import com.accenture.product.dto.InventoryDTO;
import com.accenture.product.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderRemoteService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceUrl orderPropUrlConfig;

    public ProductDTO invokeGetProductService(String name) {
        String productFindUrl = orderPropUrlConfig.getProductFindUrl(name);
        LOG.info("invokeGetProductService : productFindUrl {}", productFindUrl);
        ResponseEntity<ProductDTO> res = restTemplate.exchange(productFindUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public String invokeDeleteProductService(String productCode) {
        String productUrl = orderPropUrlConfig.deleteProductUrl(productCode);
        LOG.info("invokeGetProductService : productFindUrl {}", productUrl);
        ResponseEntity<String> res = restTemplate.exchange(productUrl, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public InventoryDTO invokeGetInventoryService(String productCode) {
        String inventoryUrl = orderPropUrlConfig.getInventoryByProductCodeUrl(productCode);
        LOG.info("invokeGetInventoryService : inventoryUrl {}", inventoryUrl);
        ResponseEntity<InventoryDTO> res = restTemplate.exchange(inventoryUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public InventoryDTO invokeUpdateInventoryService(Long invCode, Integer qty) {
        String inventoryUrl = orderPropUrlConfig.updateInventoryByProductCodeUrl(invCode, qty);
        LOG.info("invokeUpdateInventoryService : inventoryUrl {}", inventoryUrl);
        ResponseEntity<InventoryDTO> res = restTemplate.exchange(inventoryUrl, HttpMethod.PUT, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public String invokeDeleteInventoryService(Long invCode) {
        String inventoryUrl = orderPropUrlConfig.deleteInventoryServiceUrl(invCode);
        LOG.info("invokeDeleteInventoryService : inventoryUrl {}", inventoryUrl);
        ResponseEntity<String> res = restTemplate.exchange(inventoryUrl, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }
}
