package ru.egarschool.naapplication.Corporate.portal.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.ReportDto;
import ru.egarschool.naapplication.Corporate.portal.service.ReportServiceImpl;


/**
 *  Контроллер сущности Отчёта, здесь реализованы все методы CRUD
 *  Внедрение сервиса осуществляется через @RequiredArgsConstructor
 *  Доступ только авторизированным пользователям, разделены возможности ролей USER и ADMIN
 *  url: /all_reports/**
 *  Безопасность:
 *  reportServiceImpl.getOwnerUsername(#id) - получает юзернейм сотрудника владельца отчёта,
 *  и сравнивает его с юзернеймом авторизированного на данный момент сотрудника
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("all_reports")
public class ReportController {
    private final ReportServiceImpl reportService;


    /**
     * Метод отображения всех отчётов
     * @param model - модель для отображения в представлении
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping()
    public String getAllReports(Model model){
        model.addAttribute("reports", reportService.findAll());
        return "all_reports";
    }


    /**
     * Метод отображения формы создания отчёта
     * @param model - модель для отображения и подстановки значений в представлении
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/add_report")
    public String getAddReportForm(Model model){
        model.addAttribute("report", new ReportDto());
        return "add_report";
    }


    /**
     * Создание сотрудника, доступ только Администратору
     * @param reportDto - параметр для приёма и передачи полей из представления, подставленных @ModelAttribute
     * @param bindingResult - параметр валидации для reportDto, вся валидация указана в ReportDto
     */
    @PostAuthorize("hasRole('ROLE_ADMIN') or #reportDto.reportEmploy.userAccount.username == authentication.name")
    @PostMapping("/add_report")
    public String create(@Valid @ModelAttribute ReportDto reportDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "add_report";
        reportService.create(reportDto);
        return "redirect:/all_reports";
    }

    /**
     * Отображение отчёта
     * @param model - подставляет значения из полученного от сервиса сотрудника
     * @param id - идентификатор сотрудника, также используется для представления
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reportServiceImpl.getOwnerUsername(#id) == authentication.name")
    @GetMapping("/{id}")
    public String getInfoReport(Model model, @PathVariable Long id){
        model.addAttribute("report", reportService.getById(id));
        return "show_report";
    }

    /**
     * Получение формы обновления
     * @param model - подставляет значения из полученного от сервиса отчёта
     * @param id - идентификатор, также используется для представления
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reportServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/edit_report")
    public String getEditForm(Model model, @PathVariable Long id){
        ReportDto reportDto = reportService.getById(id);
        model.addAttribute("reportDto", reportDto);
        return "edit_report";
    }


    /**
     * метод обновления
     * @param reportDto - отчёт, из которого берутся значения для
     *                    редактирования, и подставляются в представление с помощью  @ModelAttribute
     * @param id - идентификатор отчёта, которого обновляет метод
     * @param bindingResult - валидация полец отчёта, проверка соответствий из ReportDto
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reportServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @PostMapping("/{id}/edit_report")
    public String update(@Valid @ModelAttribute ReportDto reportDto, BindingResult bindingResult,
                         @PathVariable Long id, Model model){
        if(bindingResult.hasErrors())
            return "edit_report";

        model.addAttribute("report", reportService.getById(id));
        reportService.update(reportDto, id);
        return "redirect:/all_reports/" + id;
    }


    /**
     * Метод удаления сотрудника по id, доступен только администратору
     * @param id - идентификатор удаляемого сотрудника
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reportServiceImpl.getOwnerUsername(#id)  == authentication.name")
    @GetMapping("/{id}/delete")
    public String deleteReport(@PathVariable Long id){
        reportService.delete(id);
        return "redirect:/all_reports";
    }

}
