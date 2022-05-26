package com.burger.burger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GenericGenerator(
            name = "uuid2",
            strategy = "uuid2"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "uuid2")
    private UUID id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_phone")
    private String orderPhone;

    @Column(name = "order_fast")
    private Boolean orderFast;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_price")
    private BigDecimal orderPrice;

    @OneToMany
    @Cascade({CascadeType.DELETE, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    private List<OrderProducts> orderProducts;
}
