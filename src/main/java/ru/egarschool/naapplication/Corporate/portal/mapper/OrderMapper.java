package ru.egarschool.naapplication.Corporate.portal.mapper;

import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;

public class OrderMapper {
    public static OrderEntity getOrder(OrderEntity order, OrderDto orderDto) {
        order.setTitle(orderDto.getTitle());
        order.setCreated(orderDto.getCreated());
        order.setDescription(orderDto.getDescription());
        order.setOrderEmploy(orderDto.getOrderEmploy());
        return order;
    }

    public static OrderDto getOrderDto(OrderEntity order) {
        return OrderDto.builder()
                .title(order.getTitle())
                .created(order.getCreated())
                .description(order.getDescription())
                .orderEmploy(order.getOrderEmploy())
                .build();
    }
}
