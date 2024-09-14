package ru.egarschool.naapplication.Corporate.portal.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.egarschool.naapplication.Corporate.portal.entity.enums.Role;
import ru.egarschool.naapplication.Corporate.portal.entity.enums.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
public class TaskEntity {
    /**
     * id - индентификатор, задаётся автоматически в порядке добавления
     * title - название задачи
     * description - описание задачи
     * whoGaveTask - сотрудник, тот КТО ДАЛ задачу
     * whoGivenTask - сотрудник, тот КОМУ ДАНА задача
     * whoGivenTask - сотрудник, тот КОМУ ДАНА задача
     * created - дата создания, задаётся автоматичски при создании сущности
     * updated - дата редактирования, задаётся автоматичски при редактировании сущности
     * Status - статус Задачи, задаётся через Enum TaskStatus
     * deadline - предполагаемая дата завершени, считается в сервисе как дата создания + timeAllowed
     * timeAllowed - количество времени на задачу
     * report - связанный с задаче отчёт
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity whoGaveTask;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity whoGivenTask;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    private LocalDateTime deadline; // предполагаемая дата дедлайна, считается в сервисе как дата создания + timeAllowed
    private LocalDateTime completed; // дата фактического завершения задачи, задаётся кнопкой "завершить",
    private Integer timeAllowed; // количество времени на задачу
    private Integer timeCancelled; // количество фактически затраченного времени



    /** Поменять связ на ManyToOne чтобы один отчёт мог ссылаться на несколько задач
     *
     *
     */
    @OneToOne(mappedBy = "linkedTask")
    private ReportEntity report;

}
