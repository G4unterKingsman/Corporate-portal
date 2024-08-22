package ru.egarschool.naapplication.Corporate.portal.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(schema = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate created;
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private EmployeeEntity whoGaveTask;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private EmployeeEntity whoGivenTask;


}
