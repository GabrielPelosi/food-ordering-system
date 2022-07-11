package com.food.ordering.system.order.service.domain.dto.create;

import com.food.ordering.system.domain.valueObject.OrderStatus;
import com.food.ordering.system.order.service.domain.valueObject.TrackingId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @NotNull
    private final UUID order;

    @NotNull
    private final OrderStatus orderStatus;
    @NotNull
    private final String message;
}