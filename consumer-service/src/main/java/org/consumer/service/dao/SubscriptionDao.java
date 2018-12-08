package org.consumer.service.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubscriptionDao {

    @NotNull(message = "Subscription is missing")
    private Boolean subscription;
}
