package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.service.OrderServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping()
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "order";
    }


    @GetMapping("/add_order")
    public String getAddOrderForm(Model model, Long id){
        model.addAttribute("order", new OrderEntity());
        return "add_order";
    }

    @PostMapping("/add_order")

    public String saveOrder(@Valid @ModelAttribute OrderEntity orderEntity, Long employId){
        orderService.create(orderEntity, employId);
        return "redirect:/order";
    }


}
