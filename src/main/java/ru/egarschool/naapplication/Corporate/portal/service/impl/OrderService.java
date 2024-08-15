package ru.egarschool.naapplication.Corporate.portal.service.impl;

import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    public List<OrderEntity> findAll();
    public OrderEntity create(OrderEntity orderEntity, Long emplId);
}
