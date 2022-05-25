package com.burger.burger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "order_products")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderProductsId.class)
public class OrderProducts {

    @Id
    private String ingredient;

    @Id
    private UUID orderId;

    @Column(name = "count_ingredient")
    private Integer quantity;


}
