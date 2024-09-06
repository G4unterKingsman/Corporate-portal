package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.service.EmployeeServiceImpl;


/**
 *  Контроллер сущности Сотрудника, здесь реализованы все методы CRUD
 *  Внедрение сервиса осуществляется через @RequiredArgsConstructor
 *  Доступ только авторизированным пользователям, разделены возможности ролей USER и ADMIN
 *  url: /all_employees/**
 *  Безопасность:
 *  employeeServiceImpl.getOwnerUsername(#id) - получает юзернейм сотрудника владельца профилья,
 *  и сравнивает его с юзернеймом авторизированного на данный момент сотрудника
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("all_employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;


    /**
     * Метод отображения всех сотрудников
     * @param model - модель для отображения в представлении
     */
    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String findAll(Model model){
        model.addAttribute("employees",employeeService.getAll());
        return "all_employees";
    }


    /**
     * Метод отображения формы создания сотрудника
     * @param model - модель для отображения и подстановки значений в представлении
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add_employee")
    public String getCreateForm(Model model){
        model.addAttribute("employeeDto", new EmployeeDto());
        return "add_employee";
    }


    /**
     * Создание сотрудника, доступ только Администратору
     * @param employeeDto - параметр для приёма полей из представления, подставленных @ModelAttribute
     * @param bindingResult - параметр валидации для employeeDto, вся валидация указана в EmployeeDto
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_employee")
    public String create(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return "add_employee";

        employeeService.create(employeeDto);
        return "redirect:/all_employees";
    }


    /**
     * Отображение профиля сотрудника
     * @param model - подставляет значения из полученного от сервиса сотрудника
     * @param id - идентификатор сотрудника, также используется для представления
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @employeeServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}")
    public String getInfoEmployee(Model model, @PathVariable Long id){
        model.addAttribute("employee", employeeService.getById(id));
        return "show_employee";
    }


    /**
     * Получение формы обновления
     * @param model - подставляет значения из полученного от сервиса сотрудника
     * @param id - идентификатор сотрудника, также используется для представления
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @employeeServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/edit_employee")
    public String getUpdateForm(Model model, @PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getById(id);
        model.addAttribute("employeeDto",employeeDto);
        return "edit_employee";
    }


    /**
     * Получение формы обновления
     * @param employeeDto - сотрудник, из которого берутся значения для
     *                    редактирования и подставляются в представление с помощью  @ModelAttribute
     * @param id - идентификатор сотрудника, которого обновляет метод
     * @param bindingResult - валидация полец сотрудника, проверка соответствий из EmployeeDto
     */
    @PostAuthorize("hasRole('ROLE_ADMIN') or #employeeDto.userAccount.username == authentication.name")
    @PostMapping("/{id}/edit_employee")
    public String update(@Valid @ModelAttribute("employee") EmployeeDto employeeDto, BindingResult bindingResult,
                         @PathVariable Long id){
        if(bindingResult.hasErrors())
            return "edit_employee";
        employeeService.update(employeeDto, id);
        return "redirect:/all_employees";
    }


    /**
     * Метод удаления сотрудника по id, доступен только администратору
     * @param id - идентификатор удаляемого сотрудника
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return "redirect:/all_employees";
    }
}
