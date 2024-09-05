package ru.egarschool.naapplication.Corporate.portal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    @Size(min= 2, max= 50, message = "Название должно занимать от 2-х до 50-ти символов")
    @NotBlank(message = "Обязательно для заполнения")
    private String title;

    @UpdateTimestamp
    private LocalDateTime created;

    @Size(min= 5, max= 500, message = "Описание должно занимать от 5-х до 500-та символов")
    @NotBlank(message = "Обязательно для заполнения")
    private String description;

    @JsonIgnore
    private EmployeeEntity whoGaveTask;

    @JsonIgnore
    @NotNull(message = "Обязательно для заполнения")
    private EmployeeEntity whoGivenTask;
}
