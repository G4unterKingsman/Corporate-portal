package ru.egarschool.naapplication.Corporate.portal.service.impl;

import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.EmployeeMapper;

import java.util.List;

public interface OrderService {
    public List<OrderDto> findAll();

    public OrderDto findById(Long id);

    public OrderDto create(OrderDto orderDto);

    public OrderDto update(OrderDto orderDto, Long id);
}
