package ru.egarschool.naapplication.Corporate.portal.service.intefraces;

import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAll();

    OrderDto getById(Long id);

    OrderDto create(OrderDto orderDto);

    OrderDto update(OrderDto orderDto, Long id);


    void delete(Long id);

}
