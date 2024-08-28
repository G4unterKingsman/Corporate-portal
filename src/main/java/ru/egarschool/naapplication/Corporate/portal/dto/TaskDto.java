package ru.egarschool.naapplication.Corporate.portal.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    @Size(min= 2, max= 50, message = "Название должно занимать от 2-х до 50-ти символов")
    private String title;
    private LocalDateTime created;

    @Size(min= 5, max= 500, message = "Описание должно занимать от 5-х до 500-та символов")
    private String description;
    private EmployeeEntity whoGaveTask;
    private EmployeeEntity whoGivenTask;
}
