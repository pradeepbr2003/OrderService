package com.accenture.product.service;

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

import static com.accenture.product.enums.UrlEnum.*;

@Service
public class OrderRemoteService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;

    public ProductDTO invokeGetProductService(String name) {
        String productFindUrl = String.format(PROD_FORMAT_URL.value(), PRODUCT_FIND_URL.value(), name);
        LOG.info("invokeGetProductService : productFindUrl {}", productFindUrl);
        ResponseEntity<ProductDTO> res = restTemplate.exchange(productFindUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public String invokeDeleteProductService(String productCode) {
        String productUrl = String.format(PROD_DELETE_FORMAT_URL.value(), PRODUCT_URL.value(), productCode);
        LOG.info("invokeGetProductService : productFindUrl {}", productUrl);
        ResponseEntity<String> res = restTemplate.exchange(productUrl, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public InventoryDTO invokeGetInventoryService(String productCode) {
        String inventoryUrl = String.format(INV_FIND_FORMAT_URL.value(), INVENTORY_FIND_URL.value(), productCode);
        LOG.info("invokeGetInventoryService : inventoryUrl {}", inventoryUrl);
        ResponseEntity<InventoryDTO> res = restTemplate.exchange(inventoryUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public InventoryDTO invokeUpdateInventoryService(Long invCode, Integer qty) {
        String inventoryUrl = String.format(INV_UPDATE_FORMAT_URL.value(), INVENTORY_URL.value(), invCode, qty);
        LOG.info("invokeUpdateInventoryService : inventoryUrl {}", inventoryUrl);
        ResponseEntity<InventoryDTO> res = restTemplate.exchange(inventoryUrl, HttpMethod.PUT, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public String invokeDeleteInventoryService(Long invCode) {
        String inventoryUrl = String.format(INV_DELETE_FORMAT_URL.value(), INVENTORY_URL.value(), invCode);
        LOG.info("invokeDeleteInventoryService : inventoryUrl {}", inventoryUrl);
        ResponseEntity<String> res = restTemplate.exchange(inventoryUrl, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }
}
