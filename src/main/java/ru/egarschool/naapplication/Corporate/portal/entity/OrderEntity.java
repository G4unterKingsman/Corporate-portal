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
@Table(name = "orders")
public class OrderEntity {

    /**
     * id - индентификатор, задаётся автоматически в порядке добавления
     * title - название отчёта
     * created - дата создания, задаётся автоматичски при создании сущности
     * description - описание отчёта
     * orderEmploy - владелец отчёта, сотрудник
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
    private EmployeeEntity orderEmploy;
}
