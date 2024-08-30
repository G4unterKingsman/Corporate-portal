package ru.egarschool.naapplication.Corporate.portal.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// todo Найти рег-выражение для имени и должности @Pattern(regexp = "//", message = "Неподдерживаемые символы")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;
    @Size(min= 2, max= 50, message = "Имя должно занимать от 2-х до 50-ти символов")
    private String name;
    @Range(min= 18, message = "ты слишком молод для этой работы, чел")
    private Integer age;
    private String post;
    private LocalDate joined;
    @Range(max= 40, message = "вы слишком стар для это работы, сэр")
    private Integer workExperienceYears;
    private String description;
    private List<OrderEntity> employOrders = new ArrayList<>();
    private List<TaskEntity> taskForEmploy = new ArrayList<>();
    private List<TaskEntity> taskFromEmploy = new ArrayList<>();
}
