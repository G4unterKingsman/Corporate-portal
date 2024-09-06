package ru.egarschool.naapplication.Corporate.portal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /**
     * id - индентификатор, задаётся автоматически в порядке добавления
     * name - Имя сотрудника в формате "Имя Фамилия"
     * age - возраст сотрудника
     * joined - дата присоединения
     * workExperienceYears - стаж работы
     * description - описание/пометка о сотруднике
     * employOrders - список отчётов сотрудника
     * taskForEmploy - список задач ДЛЯ сотрудника ОТ других сотрудников
     * taskFromEmploy- список задач ОТ сотрудника ДЛЯ других сотрудников
     * userAccount - привязанный аккаунт, под которым сотрудник входит в портал
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private LocalDate joined;
    private Integer workExperienceYears;
    private String description;

    @OneToMany(mappedBy = "orderEmploy", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderEntity> employOrders = new ArrayList<>();

    @OneToMany(mappedBy = "whoGaveTask", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskEntity> taskForEmploy = new ArrayList<>();

    @OneToMany(mappedBy = "whoGivenTask", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskEntity> taskFromEmploy = new ArrayList<>();

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAccount userAccount;
}
