package com.accenture.product.config;

import com.accenture.product.config.factory.YamlPropSourceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:service_url.yaml", factory = YamlPropSourceFactory.class)
@ConfigurationProperties(prefix = "order.product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductPropConfig {
    private String url;
    private String findUrl;
    private String formatUrl;
    private String deleteFormatUrl;
}
