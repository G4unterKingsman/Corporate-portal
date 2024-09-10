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
     * created - дата создания, задаётся автоматичски при создании сущности
     * description - описание задачи
     * whoGaveTask - сотрудник, тот КТО ДАЛ задачу
     * whoGivenTask - сотрудник, тот КОМУ ДАНА задача
     *
     *
     *
     * 1. в TASK добавить статус, который меняется в момент создания, взятия в работу,
     *          завершения либо невыполнения в срок.
     *
     * Также в TASK добавить время, отведенное на выполнение и фактически затраченное на данный момент.
     * В отчете сделать не столько абстрактное описание, сколько привязать к отчету все таски данного сотрудника
     * (Т.е. добавить в отчет связь один ко многим - Отчет-таски).
     * Туда можно добавить время, отведенное на таски,
     * затраченное на данный момент,
     * сколько тасок подходят к дедлайну, какие именно.
     *
     * Количество тасок в разных статусах.
     * Так сущности хоть немного завяжутся друг на друга и начнут взаимодействовать.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @CreationTimestamp
    private LocalDateTime created;
    private String description;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity whoGaveTask;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity whoGivenTask;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Integer timeAllowed;

    private Integer timeCancel;


}
