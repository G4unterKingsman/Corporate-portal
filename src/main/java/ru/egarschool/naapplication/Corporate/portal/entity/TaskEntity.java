package ru.egarschool.naapplication.Corporate.portal.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
     * created - дата создания, задаётся автоматичски при создании сущности
     * description - описание задачи
     * whoGaveTask - сотрудник, тот КТО ДАЛ задачу
     * whoGivenTask - сотрудник, тот КОМУ ДАНА задача
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @UpdateTimestamp
    private LocalDateTime created;
    private String description;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity whoGaveTask;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity whoGivenTask;


}
