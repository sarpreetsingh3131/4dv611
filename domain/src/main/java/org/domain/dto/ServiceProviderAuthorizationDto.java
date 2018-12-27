package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ServiceProviderAuthorizationDto {

    @NotNull(message = "serviceProviderId is missing")
    private Long serviceProviderId;

    @NotNull(message = "authorization is missing")
    private Boolean authorization;
}
