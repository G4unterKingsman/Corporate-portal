package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public String getAllTasks(Model model){
        model.addAttribute("tasks", taskService.findAll());
        return "all_tasks";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/add_task")
    public String getAddTaskForm(Model model){
        model.addAttribute("task", new TaskDto());
        return "add_task";
    }


    @PostAuthorize("hasRole('ROLE_ADMIN') or #taskDto.whoGaveTask.userAccount.username == authentication.name")
    @PostMapping("/add_task")
    public String create(@Valid @ModelAttribute TaskDto taskDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "add_task";
        taskService.create(taskDto);
        return "redirect:/all_tasks";
    }

    /**
     * Метод получение представления show_task  - информации о задаче
     * Проверка безопасности: проверяется является зи сотрудник Админом, тем кто дал задачу, или тем кому дана задача
     * @param id - идентификатор задачи
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @taskServiceImpl.getOwnerUsername(#id)  == authentication.name  " +
                                        "or @taskServiceImpl.getAssigneeUsername(#id) == authentication.name")
    @GetMapping("/{id}")
    public String getInfoTask(Model model, @PathVariable Long id){
        model.addAttribute("task", taskService.getById(id));
        return "show_task";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or @taskServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/edit_task")
    public String getEditForm(Model model, @PathVariable Long id){
        TaskDto taskDto = taskService.getById(id);
        model.addAttribute("taskDto",taskDto);
        return "edit_task";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or @taskServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @PostMapping("/{id}/edit_task")
    public String update(@Valid @ModelAttribute TaskDto taskDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        model.addAttribute("task",taskService.getById(id));
        if(bindingResult.hasErrors())
            return "edit_task";
        taskService.update(taskDto, id);
        return "redirect:/all_tasks/{id}";
    }



    @PreAuthorize("hasRole('ROLE_ADMIN') or @taskServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return "redirect:/all_tasks";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or @taskServiceImpl.getAssigneeUsername(#id)  == authentication.name")
    @GetMapping("/{id}/start")
    public String startTask(@PathVariable Long id){
        boolean isCancel = false;
        taskService.switchStatus(id,isCancel);
        return "redirect:/all_tasks/{id}";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or @taskServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/complete")
    public String completeTask(@PathVariable Long id){
        boolean isCancel = false;
        taskService.switchStatus(id,isCancel);
        return "redirect:/all_tasks/{id}";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or @taskServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/cancel")
    public String cancelTask(@PathVariable Long id){
        boolean isCancel = true;
        taskService.switchStatus(id,isCancel);
        return "redirect:/all_tasks/{id}";
    }
}
