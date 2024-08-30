package ru.egarschool.naapplication.Corporate.portal.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @CreationTimestamp
    private LocalDateTime created;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private EmployeeEntity whoGaveTask;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private EmployeeEntity whoGivenTask;


}
