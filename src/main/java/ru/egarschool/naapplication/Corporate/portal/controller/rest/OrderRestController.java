package ru.egarschool.naapplication.Corporate.portal.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.service.OrderServiceImpl;

import java.util.List;


@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderServiceImpl orderService;
    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id){
        return orderService.getById(id);
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return orderService.update(orderDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }
}
