package ru.egarschool.naapplication.Corporate.portal.mapper;

import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;

public class OrderMapper {
    public static OrderEntity getOrder(OrderDto orderDto) {
        if (orderDto == null) {return null;}
        OrderEntity order = new OrderEntity();
        order.setId(orderDto.getId());
        order.setTitle(orderDto.getTitle());
        order.setCreated(orderDto.getCreated());
        order.setOrderEmploy(orderDto.getOrderEmploy());
        order.setDescription(orderDto.getDescription());
        return order;
    }

    public static OrderDto getOrderDto(OrderEntity order) {
        if (order == null) {return null;}
        return OrderDto.builder()
                .id(order.getId())
                .title(order.getTitle())
                .description(order.getDescription())
                .created(order.getCreated())
                .orderEmploy(order.getOrderEmploy())
                .build();
    }
}
