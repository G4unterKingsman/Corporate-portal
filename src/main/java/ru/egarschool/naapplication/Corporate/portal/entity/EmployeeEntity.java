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


    @OneToMany(mappedBy = "orderEmploy", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderEntity> employOrders = new ArrayList<>();


    @OneToMany(mappedBy = "whoGaveTask", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskEntity> taskForEmploy = new ArrayList<>();

    @OneToMany(mappedBy = "whoGivenTask", cascade = CascadeType.REMOVE, orphanRemoval = true)

    private List<TaskEntity> taskFromEmploy = new ArrayList<>();



    //TODO: Сделать эндпоинты для задания и отчёта, добавить в их контроллеры обработку
    //TODO: дописать все функции CRUD для КАЖДОЙ СУЩНОСТИ
    //TODO: Добавить сущность админа команды? МногоКОдному создание задания,удаление сотрудников,просмотр отчётов всех


}
