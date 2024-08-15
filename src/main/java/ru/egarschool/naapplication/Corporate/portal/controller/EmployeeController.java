package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.service.EmployeeServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("employee",employeeService.findAll());
        return "index";
    }

    @GetMapping("/add_employee")
    public String getAddForm(Model model){
        model.addAttribute("employee", new EmployeeEntity());
        return "add_employee";
    }

    @PostMapping("/add_employee")
    public String create(@Valid @ModelAttribute EmployeeEntity employeeEntity){
        employeeService.create(employeeEntity);
        return "redirect:/employee";
    }



    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }
}
