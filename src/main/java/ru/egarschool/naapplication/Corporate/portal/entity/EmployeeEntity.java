package ru.egarschool.naapplication.Corporate.portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String post;
    private LocalDate joined;
    private Integer workExperienceYears;
    private String description;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEmploy")
    private List<OrderEntity> employOrders = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoGaveTask")
    private List<TaskEntity> taskForEmploy = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoGivenTask")
    private List<TaskEntity> taskFromEmploy = new ArrayList<>();



    //TODO: Сделать эндпоинты для задания и отчёта, добавить в их контроллеры обработку
    //TODO: дописать все функции CRUD для КАЖДОЙ СУЩНОСТИ
    //TODO: Добавить сущность админа команды? МногоКОдному создание задания,удаление сотрудников,просмотр отчётов всех


}
