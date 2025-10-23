package com.accenture.product.enums;

public enum UrlEnum {
    PRODUCT_URL("http://localhost:8080/product"),
    PROD_DELETE_FORMAT_URL("%s?code=%s"),
    PRODUCT_FIND_URL("http://localhost:8080/product/find"),
    PROD_FORMAT_URL("%s?name=%s"),
    INVENTORY_URL("http://localhost:8081/inventory"),
    INVENTORY_FIND_URL("http://localhost:8081/inventory/find"),
    INV_FIND_FORMAT_URL("%s?productCode=%s"),
    INV_DELETE_FORMAT_URL("%s?code=%s"),
    INV_UPDATE_FORMAT_URL("%s?code=%s&qty=%s");
    private String url;

    UrlEnum(String url) {
        this.url = url;
    }

    public String value() {
        return url;
    }
}
