package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.service.EmployeeServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("all_employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("employees",employeeService.getAll());
        return "all_employees";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add_employee")
    public String getCreateForm(Model model){
        model.addAttribute("employee", new EmployeeDto());
        return "add_employee";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_employee")
    public String create(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "add_employee";

        employeeService.create(employeeDto);
        return "redirect:/all_employees";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public String getInfoEmployee(Model model, @PathVariable Long id){
        model.addAttribute("employee", employeeService.getById(id));
        return "show_employee";
    }



    @PreAuthorize("hasRole('ROLE_USER') ")
    @GetMapping("/{id}/edit_employee")
    public String getUpdateForm(Model model, @PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getById(id);
        model.addAttribute("employeeDto",employeeDto);
        return "edit_employee";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or #employeeDto.userAccount.username == authentication.name")
    @PostMapping("/{id}/edit_employee")
    public String update(@Valid @ModelAttribute("employee") EmployeeDto employeeDto, BindingResult bindingResult,
                         @PathVariable Long id){
        if(bindingResult.hasErrors())
            return "edit_employee";
        employeeService.update(employeeDto, id);
        return "redirect:/all_employees";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return "redirect:/all_employees";
    }
}
