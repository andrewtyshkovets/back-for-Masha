package com.burger.burger.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class OrderDto {

    private UUID id;

    @NotNull(message = "Order name can not be null")
    @NotEmpty(message = "Order name can not be empty")
    private String orderName;

    @NotNull(message = "Order phone can not be null")
    @NotEmpty(message = "Order name can not be empty")
    private String orderPhone;

    @NotNull(message = "Order fast can not be null")
    private Boolean orderFast;

    @NotNull(message = "Order address can not be null")
    @NotEmpty(message = "Order name can not be empty")
    private String orderAddress;

    @NotNull(message = "Order price can not be null")
    @Min(value = 1, message = "Price can not be zero")
    private BigDecimal orderPrice;

    @NotNull(message = "Order products can not be null")
    private Map<String, Integer> orderProducts;
}
