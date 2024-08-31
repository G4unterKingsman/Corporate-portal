package ru.egarschool.naapplication.Corporate.portal.service.impl;

import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;

import java.util.List;

public interface OrderService {
    public List<OrderDto> findAll();

    public OrderDto getById(Long id);

    public OrderDto create(OrderDto orderDto);

    public OrderDto update(OrderDto orderDto, Long id);
}
