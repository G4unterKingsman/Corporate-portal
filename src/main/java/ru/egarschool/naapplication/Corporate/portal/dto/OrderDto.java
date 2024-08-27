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
public class OrderDto {
    @Size(min= 2, max= 50, message = "Имя должно занимать от 2-х до 50-ти символов")
    private String title;
    private LocalDateTime created;
    private String description;
    private EmployeeEntity orderEmploy;
}
