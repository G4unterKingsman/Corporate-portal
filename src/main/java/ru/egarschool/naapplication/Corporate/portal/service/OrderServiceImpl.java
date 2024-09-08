package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.exception.OrderNotFoundException;
import ru.egarschool.naapplication.Corporate.portal.mapper.OrderMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.OrderRepo;
import ru.egarschool.naapplication.Corporate.portal.service.intefraces.OrderService;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final EmployeeRepo employeeRepo;
    private final SecurityService securityService;

    public List<OrderDto> findAll() {
        List<OrderEntity> orders = orderRepo.findAll();
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderDto getById(Long id){
        OrderEntity order =  orderRepo.findById(id).orElseThrow(
                () ->  new OrderNotFoundException("Отчёт с идентификатором " + id + " не найден"));
        return orderMapper.toDto(order);
    }


    public OrderDto create(OrderDto orderDto){
        String username = securityService.getCurrentUsername();
        EmployeeEntity employee = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        orderDto.setOrderEmploy(employee);

        OrderEntity order = orderMapper.toEntity(orderDto);
        orderRepo.save(order);
        return orderDto;
    }

    public OrderDto update(OrderDto orderDto, Long id) {
        String username = securityService.getCurrentUsername();
        EmployeeEntity employee = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        orderDto.setOrderEmploy(employee);

        OrderEntity order = orderRepo.findById(id).orElseThrow(
                () ->  new OrderNotFoundException("Отчёт с идентификатором " + id + " не найден"));
        order.setOrderEmploy(employee);
        orderMapper.toUpdateOrderFromDto(orderDto,order);

        orderRepo.save(order);
        return orderDto;
    }

    @Override
    public void delete(Long id) {
        orderRepo.deleteById(id);
    }


    public String getOwnerUsername(Long id) {
        OrderEntity order = orderRepo.findById(id).orElseThrow(
                () ->  new OrderNotFoundException("Отчёт с идентификатором " + id + " не найден"));
        return order.getOrderEmploy().getUserAccount().getUsername();
    }
}
