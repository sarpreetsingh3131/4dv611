package org.consumer.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubscriptionDto {

    @NotNull(message = "Subscription is missing")
    private Boolean subscription;
}
