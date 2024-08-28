package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.TaskMapper;
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
    public String getAddTaskForm(Model model, Long idWhoGave, Long idWhoGiven){
        model.addAttribute("task", new TaskEntity());
        return "add_task";
    }


    @PostMapping("/add_task")
    public String saveTask(@Valid @ModelAttribute TaskEntity taskEntity, Long idWhoGave, Long idWhoGiven){
        taskService.create(taskEntity, idWhoGave, idWhoGiven);
        return "redirect:/all_tasks";
    }

    @GetMapping("/{taskId}")
    public String getInfoTask(Model model, @PathVariable Long taskId){
        model.addAttribute("task", taskService.findById(taskId));
        return "show_task";
    }




    @GetMapping("/{id}/edit_task")
    public String getEditForm(Model model, @PathVariable Long id){
        TaskEntity task = taskService.findById(id);
        model.addAttribute("task", task);
        TaskDto taskDto = TaskMapper.getTaskDto(task);
        model.addAttribute("taskDto",taskDto);
        return "edit_task";
    }

    @PostMapping("/{id}/edit_task")
    public String update(@Valid @ModelAttribute TaskDto taskDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        TaskEntity task = taskService.findById(id);
        model.addAttribute("task",task);
        if(bindingResult.hasErrors())
            return "edit_task";
        taskService.update(task, taskDto);
        return "redirect:/all_tasks/{id}";
    }
}
