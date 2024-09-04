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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private LocalDate joined;
    private Integer workExperienceYears;
    private String description;

    @OneToMany(mappedBy = "orderEmploy", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<OrderEntity> employOrders = new ArrayList<>();

    @OneToMany(mappedBy = "whoGaveTask", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<TaskEntity> taskForEmploy = new ArrayList<>();

    @OneToMany(mappedBy = "whoGivenTask", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<TaskEntity> taskFromEmploy = new ArrayList<>();

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private UserAccount userAccount;
}
