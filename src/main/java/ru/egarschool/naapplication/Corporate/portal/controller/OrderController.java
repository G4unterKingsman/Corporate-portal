package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.OrderMapper;
import ru.egarschool.naapplication.Corporate.portal.service.OrderServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("all_orders")
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping()
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "all_orders";
    }


    @GetMapping("/add_order")
    public String getAddOrderForm(Model model){
        model.addAttribute("order", new OrderDto());
        return "add_order";
    }

    @PostMapping("/add_order")
    public String create(@Valid @ModelAttribute OrderDto orderDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "edit_order";
        orderService.create(orderDto);
        return "redirect:/all_orders";
    }

    @GetMapping("/{id}")
    public String getInfoOrder(Model model, @PathVariable Long id){
        model.addAttribute("order", orderService.findById(id));
        return "show_order";
    }


    @GetMapping("/{id}/edit_order")
    public String getEditForm(Model model, @PathVariable Long id){
        OrderDto orderDto = orderService.findById(id);
        model.addAttribute("orderDto", orderDto);
        return "edit_order";
    }

    @PostMapping("/{id}/edit_order")
    public String update(@Valid @ModelAttribute OrderDto orderDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        model.addAttribute("order",orderService.findById(id));
        if(bindingResult.hasErrors())
            return "edit_order";
        orderService.update(orderDto, id);
        return "redirect:/all_orders/{id}";
    }

}
