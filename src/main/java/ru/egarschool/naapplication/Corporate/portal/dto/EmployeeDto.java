package ru.egarschool.naapplication.Corporate.portal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ru.egarschool.naapplication.Corporate.portal.entity.ReportEntity;
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
    @NotBlank(message = "Обязательно для заполнения")
    private String name;

    @Range(min= 18,max= 70, message = "Возраст от 18 до 70")
    @NotNull(message = "Обязательно для заполнения")
    private Integer age;

    @NotNull(message = "Обязательно для заполнения")
    private LocalDate joined;

    @Range(max= 70, message = "А Вы не староват?")
    @NotNull(message = "Обязательно для заполнения")
    private Integer workExperienceYears;

    @NotBlank(message = "Обязательно для заполнения")
    private String description;


    @JsonIgnore
    private List<ReportEntity> employeeReports = new ArrayList<>();
    @JsonIgnore
    private List<TaskEntity> taskForEmploy = new ArrayList<>();
    @JsonIgnore
    private List<TaskEntity> taskFromEmploy = new ArrayList<>();

    @JsonIgnore
    @NotNull(message = "Обязательно для заполнения")
    private UserAccount userAccount;
}
