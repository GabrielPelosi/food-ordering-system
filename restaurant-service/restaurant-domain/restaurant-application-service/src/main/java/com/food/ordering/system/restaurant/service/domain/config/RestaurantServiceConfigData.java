package com.food.ordering.system.restaurant.service.domain.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Data
@ConfigurationProperties(prefix = "restaurant-service")
public class RestaurantServiceConfigData {

    private String restaurantApprovalRequestTopicName;
    private String restaurantApprovalResponseTopicName;

}
