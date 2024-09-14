package ru.egarschool.naapplication.Corporate.portal.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reports")
public class ReportEntity {

    /**
     * id - индентификатор, задаётся автоматически в порядке добавления
     * title - название отчёта
     * created - дата создания, задаётся автоматичски при создании сущности
     * description - описание отчёта
     * reportEmploy - владелец отчёта, сотрудник
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    private String description;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity reportEmploy;

    @OneToOne
    @JoinColumn
    private TaskEntity linkedTask;
}
