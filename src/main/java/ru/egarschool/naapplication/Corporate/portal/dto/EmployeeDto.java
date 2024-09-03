package ru.egarschool.naapplication.Corporate.portal.dto;


import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;
    @Size(min= 2, max= 50, message = "Имя должно занимать от 2-х до 50-ти символов")
    @Pattern(regexp = "^[A-ZА-ЯЁ][a-zа-яё]+\\s[A-ZА-ЯЁ][a-zа-яё]+$", message = "Некорректное имя!")
    private String name;
    @Range(min= 18,max= 70, message = "Возраст от 18 до 70")
    private Integer age;
    private String post;
    private LocalDate joined;
    @Range(max= 40, message = "вы слишком стар для этой работы, сэр")


    @Range(max= 99, message = "А Вы не староват?")
    private Integer workExperienceYears;
    private String description;
    private List<OrderEntity> employOrders = new ArrayList<>();
    private List<TaskEntity> taskForEmploy = new ArrayList<>();
    private List<TaskEntity> taskFromEmploy = new ArrayList<>();
    private UserAccount userAccount;
}
