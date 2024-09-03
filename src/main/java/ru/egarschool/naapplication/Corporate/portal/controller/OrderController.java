package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.service.OrderServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("all_orders")
public class OrderController {
    private final OrderServiceImpl orderService;



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "all_orders";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add_order")
    public String getAddOrderForm(Model model){
        model.addAttribute("order", new OrderDto());
        return "add_order";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #orderDto.orderEmploy.userAccount.username == authentication.name")
    @PostMapping("/add_order")
    public String create(@Valid @ModelAttribute OrderDto orderDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "add_order";
        orderService.create(orderDto);
        return "redirect:/all_orders";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public String getInfoOrder(Model model, @PathVariable Long id){
        model.addAttribute("order", orderService.getById(id));
        return "show_order";
    }



    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/edit_order")
    public String getEditForm(Model model, @PathVariable Long id){
        OrderDto orderDto = orderService.getById(id);
        model.addAttribute("orderDto", orderDto);
        return "edit_order";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or #orderDto.orderEmploy.userAccount.username == authentication.name")
    @PostMapping("/{id}/edit_order")
    public String update(@Valid @ModelAttribute OrderDto orderDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        model.addAttribute("order",orderService.getById(id));
        if(bindingResult.hasErrors())
            return "edit_order";
        orderService.update(orderDto, id);
        return "redirect:/all_orders/{id}";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id){
        orderService.delete(id);
        return "redirect:/all_orders";
    }

}
