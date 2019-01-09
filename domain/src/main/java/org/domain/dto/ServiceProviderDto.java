package org.domain.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ServiceProviderDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private Integer phone;

    @NonNull
    private String company;
}
