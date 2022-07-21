package com.food.ordering.system.order.service.application.exception.handler;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {

    private String code;
    private String message;
}
