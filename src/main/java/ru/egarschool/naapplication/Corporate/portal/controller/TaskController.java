package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.service.TaskServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("all_tasks")
public class TaskController {
    private final TaskServiceImpl taskService;

    @GetMapping
    public String getAllTasks(Model model){
        model.addAttribute("tasks", taskService.findAll());
        return "all_tasks";
    }



    @GetMapping("/add_task")
    public String getAddTaskForm(Model model){
        model.addAttribute("task", new TaskDto());
        return "add_task";
    }


    @PostMapping("/add_task")
    public String create(@Valid @ModelAttribute TaskDto taskDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "edit_task";
        taskService.create(taskDto);
        return "redirect:/all_tasks";
    }

    @GetMapping("/{id}")
    public String getInfoTask(Model model, @PathVariable Long id){
        model.addAttribute("task", taskService.getById(id));
        return "show_task";
    }




    @GetMapping("/{id}/edit_task")
    public String getEditForm(Model model, @PathVariable Long id){
        TaskDto taskDto = taskService.getById(id);
        model.addAttribute("taskDto",taskDto);
        return "edit_task";
    }

    @PostMapping("/{id}/edit_task")
    public String update(@Valid @ModelAttribute TaskDto taskDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        model.addAttribute("task",taskService.getById(id));
        if(bindingResult.hasErrors())
            return "edit_task";
        taskService.update(taskDto, id);
        return "redirect:/all_tasks/{id}";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return "redirect:/all_tasks";
    }
}
