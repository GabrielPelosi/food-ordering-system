package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntityId implements Serializable {

    private UUID id;
    private UUID productId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntityId that = (RestaurantEntityId) o;
        return id.equals(that.id) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId);
    }
}
