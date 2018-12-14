package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ServiceProviderAuthorizationDto {

    @NotNull(message = "Service provider id is missing")
    private Long serviceProviderId;

    @NotNull(message = "Service provider authorization is missing")
    private Boolean authorization;
}
