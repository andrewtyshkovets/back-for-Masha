package com.burger.burger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductsId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "order_id")
    private UUID orderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductsId)) return false;

        OrderProductsId that = (OrderProductsId) o;

        if (!ingredient.equals(that.ingredient)) return false;
        return orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        int result = ingredient.hashCode();
        result = 31 * result + orderId.hashCode();
        return result;
    }
}
