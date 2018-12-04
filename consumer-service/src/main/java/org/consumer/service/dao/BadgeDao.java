package org.consumer.service.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BadgeDao {
    @NotNull
    private Boolean badge;

    @NotNull
    private Long productId;
}
