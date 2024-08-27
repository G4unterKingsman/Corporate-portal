package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.EmployeeMapper;
import ru.egarschool.naapplication.Corporate.portal.service.EmployeeServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("employees",employeeService.findAll());
        return "all_employees";
    }

    @GetMapping("/add_employee")
    public String getAddForm(Model model){
        model.addAttribute("employee", new EmployeeEntity());
        return "add_employee";
    }

    @PostMapping("/add_employee")
    public String create(@Valid @ModelAttribute EmployeeEntity employeeEntity){
        employeeService.create(employeeEntity);
        return "redirect:/employees";
    }

    @GetMapping("/{Id}")
    public String getInfoEmployee(Model model, @PathVariable Long Id){
        model.addAttribute("employee", employeeService.findById(Id));
        return "show_employee";
    }

    @GetMapping("/{id}/edit_employee")
    public String getEditForm(Model model, @PathVariable Long id){
        EmployeeEntity employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        EmployeeDto employeeDto = EmployeeMapper.getEmployeeDto(employee);
        model.addAttribute("employeeDto",employeeDto);
        return "edit_employee";
    }

    @PostMapping("/{id}/edit_employee")
    public String update(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        EmployeeEntity employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        if(bindingResult.hasErrors())
            return "edit_employee";
        employeeService.update(employee, employeeDto);
        return "redirect:/employees";
    }
}
