package com.accenture.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceUrl {

    @Autowired
    private OrderProductPropConfig prodPropConfig;

    @Autowired
    private OrderInventoryPropConfig invPropConfig;

    public String getProductFindUrl(String name) {
        String productFindUrl = String.format(prodPropConfig.getFormatUrl(), prodPropConfig.getFindUrl(), name);
        return productFindUrl;
    }

    public String deleteProductUrl(String productCode) {
        String productUrl = String.format(prodPropConfig.getDeleteFormatUrl(), prodPropConfig.getUrl(), productCode);
        return productUrl;
    }

    public String getInventoryByProductCodeUrl(String productCode) {
        String inventoryUrl = String.format(invPropConfig.getFindFormaturl(), invPropConfig.getFindUrl(), productCode);
        return inventoryUrl;
    }

    public String updateInventoryByProductCodeUrl(Long invCode, Integer qty) {
        String inventoryUrl = String.format(invPropConfig.getUpdateFormaturl(), invPropConfig.getUrl(), invCode, qty);
        return inventoryUrl;
    }

    public String deleteInventoryServiceUrl(Long invCode) {
        String inventoryUrl = String.format(invPropConfig.getDeleteFormaturl(), invPropConfig.getUrl(), invCode);
        return inventoryUrl;
    }

}
