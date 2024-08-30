package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.EmployeeMapper;
import ru.egarschool.naapplication.Corporate.portal.mapper.OrderMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.OrderRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.EmployeeService;
import ru.egarschool.naapplication.Corporate.portal.service.impl.OrderService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final EmployeeService employeeService;

    @Override
    public List<OrderDto> findAll() {
        List<OrderEntity> orders = orderRepo.findAll();
        return orders.stream()
                .map(e -> OrderMapper.getOrderDto(e))
                .collect(Collectors.toList());
    }


    public OrderDto findById(Long id){
        OrderEntity order =  orderRepo.findById(id).orElseThrow();
        return OrderMapper.getOrderDto(order);
    }

    public OrderDto create(OrderDto orderDto){
        OrderEntity order = OrderMapper.getOrder(orderDto);
        orderRepo.save(order);
        return OrderMapper.getOrderDto(order);
    }

    public OrderDto update(OrderDto orderDto, Long id) {
        OrderEntity order = orderRepo.save(OrderMapper.getOrder(orderDto));
        return OrderMapper.getOrderDto(order);
    }

}
