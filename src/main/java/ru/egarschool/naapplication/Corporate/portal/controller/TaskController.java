package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.service.TaskServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("task")
public class TaskController {
    private final TaskServiceImpl taskService;

    @GetMapping
    public String getAllTasks(Model model){
        model.addAttribute("tasks", taskService.findAll());
        return "task";
    }



    @GetMapping("/add_task")
    public String getAddTaskForm(Model model, Long idWhoGave, Long idWhoGiven){
        model.addAttribute("task", new TaskEntity());
        return "add_task";
    }


    @PostMapping("/add_task")
    public String saveTask(@Valid @ModelAttribute TaskEntity taskEntity, Long idWhoGave, Long idWhoGiven){
        taskService.create(taskEntity, idWhoGave, idWhoGiven);
        return "redirect:/task";
    }

}
