package ru.egarschool.naapplication.Corporate.portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(schema = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Integer age;
    private String post;
    private LocalDate joined;
    private Integer workExperienceYears;
    private String description;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEmpl")
    private List<OrderEntity> emplOrders = new ArrayList<>();


}
