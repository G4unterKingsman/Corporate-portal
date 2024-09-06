package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.service.OrderServiceImpl;


/**
 *  Контроллер сущности Отчёта, здесь реализованы все методы CRUD
 *  Внедрение сервиса осуществляется через @RequiredArgsConstructor
 *  Доступ только авторизированным пользователям, разделены возможности ролей USER и ADMIN
 *  url: /all_orders/**
 *  Безопасность:
 *  orderServiceImpl.getOwnerUsername(#id) - получает юзернейм сотрудника владельца отчёта,
 *  и сравнивает его с юзернеймом авторизированного на данный момент сотрудника
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("all_orders")
public class OrderController {
    private final OrderServiceImpl orderService;


    /**
     * Метод отображения всех отчётов
     * @param model - модель для отображения в представлении
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping()
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "all_orders";
    }


    /**
     * Метод отображения формы создания отчёта
     * @param model - модель для отображения и подстановки значений в представлении
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/add_order")
    public String getAddOrderForm(Model model){
        model.addAttribute("order", new OrderDto());
        return "add_order";
    }


    /**
     * Создание сотрудника, доступ только Администратору
     * @param orderDto - параметр для приёма и передачи полей из представления, подставленных @ModelAttribute
     * @param bindingResult - параметр валидации для orderDto, вся валидация указана в OrderDto
     */
    @PostAuthorize("hasRole('ROLE_ADMIN') or #orderDto.orderEmploy.userAccount.username == authentication.name")
    @PostMapping("/add_order")
    public String create(@Valid @ModelAttribute OrderDto orderDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "add_order";
        orderService.create(orderDto);
        return "redirect:/all_orders";
    }

    /**
     * Отображение отчёта
     * @param model - подставляет значения из полученного от сервиса сотрудника
     * @param id - идентификатор сотрудника, также используется для представления
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @orderServiceImpl.getOwnerUsername(#id) == authentication.name")
    @GetMapping("/{id}")
    public String getInfoOrder(Model model, @PathVariable Long id){
        model.addAttribute("order", orderService.getById(id));
        return "show_order";
    }

    /**
     * Получение формы обновления
     * @param model - подставляет значения из полученного от сервиса отчёта
     * @param id - идентификатор, также используется для представления
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @orderServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/edit_order")
    public String getEditForm(Model model, @PathVariable Long id){
        OrderDto orderDto = orderService.getById(id);
        model.addAttribute("orderDto", orderDto);
        return "edit_order";
    }


    /**
     * Получение формы обновления
     * @param orderDto - отчёт, из которого берутся значения для
     *                    редактирования и подставляются в представление с помощью  @ModelAttribute
     * @param id - идентификатор отчёта, которого обновляет метод
     * @param bindingResult - валидация полец отчёта, проверка соответствий из OrderDto
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @orderServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @PostMapping("/{id}/edit_order")
    public String update(@Valid @ModelAttribute OrderDto orderDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        if(bindingResult.hasErrors())
            return "edit_order";

        model.addAttribute("order",orderService.getById(id));
        orderService.update(orderDto, id);
        return "redirect:/all_orders/{id}";
    }


    /**
     * Метод удаления сотрудника по id, доступен только администратору
     * @param id - идентификатор удаляемого сотрудника
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @orderServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id){
        orderService.delete(id);
        return "redirect:/all_orders";
    }

}
