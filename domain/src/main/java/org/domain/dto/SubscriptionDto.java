package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubscriptionDto {

    @NotNull(message = "subscription is missing")
    private Boolean subscription;
}
