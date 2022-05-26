package com.burger.burger.service;

import com.burger.burger.model.Order;
import com.burger.burger.model.OrderDto;
import com.burger.burger.model.OrderProducts;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public interface OrderMapper {

    @Mapping(target = "orderProducts", source = "orderProducts", qualifiedByName = "orderProductsMap")
    OrderDto orderToOrderDto(Order order);

    @Mapping(target = "orderProducts", source = ".", qualifiedByName = "orderProducts")
    Order orderDtoToOrder(OrderDto order);

    @Mappings({
            @Mapping(target = "orderProducts", source = ".", qualifiedByName = "orderProducts"),
            @Mapping(target = "id", ignore = true)})
    Order updateOrderFromDto(OrderDto orderDto, @MappingTarget Order order);

    @Named("orderProductsMap")
    default Map<String, Integer> orderToProductsMap(List<OrderProducts> orderProducts) {
        Map<String, Integer> orderProductsMap = new HashMap<>();
        orderProducts.forEach(products -> orderProductsMap.put(products.getIngredient(), products.getQuantity()));
        return orderProductsMap;
    }

    @Named("orderProducts")
    default List<OrderProducts> orderToProductsMap(OrderDto orderDto) {
        List<OrderProducts> orderProductsList = new ArrayList<OrderProducts>();
        orderDto.getOrderProducts().forEach((s, integer) -> orderProductsList.add(
                OrderProducts.builder()
                        .ingredient(s)
                        .orderId(orderDto.getId() != null ? orderDto.getId() : null)
                        .quantity(integer)
                        .build()
        ));
        return orderProductsList;
    }
}
