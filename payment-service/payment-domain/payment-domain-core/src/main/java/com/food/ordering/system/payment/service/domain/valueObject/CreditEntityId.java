package com.food.ordering.system.payment.service.domain.valueObject;

import com.food.ordering.system.domain.valueObject.BaseId;

import java.util.UUID;

public class CreditEntityId extends BaseId<UUID> {

    public CreditEntityId(UUID value) {
        super(value);
    }
}
