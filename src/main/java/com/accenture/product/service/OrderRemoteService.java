package com.accenture.product.service;

import com.accenture.product.dto.InventoryDTO;
import com.accenture.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderRemoteService {

    @Autowired
    private RestTemplate restTemplate;


    public ProductDTO invokeGetProductService(String productFindUrl) {
        ResponseEntity<ProductDTO> res = restTemplate.exchange(productFindUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public InventoryDTO invokeGetInventoryService(String inventoryUrl) {
        ResponseEntity<InventoryDTO> res = restTemplate.exchange(inventoryUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public InventoryDTO invokeUpdateInventoryService(String inventoryUrl) {
        ResponseEntity<InventoryDTO> res = restTemplate.exchange(inventoryUrl, HttpMethod.PUT, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }

    public String invokeDeleteInventoryService(String inventoryUrl) {
        ResponseEntity<String> res = restTemplate.exchange(inventoryUrl, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
        });
        return res.getBody();
    }
}
