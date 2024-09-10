package ru.egarschool.naapplication.Corporate.portal.entity;

import ru.egarschool.naapplication.Corporate.portal.entity.enums.Role;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserAccount {
    /**
     * id - индентификатор, задаётся автоматически в порядке добавления
     * username - юзер-нейм сотрудника под которым он авторизируется
     * password - пароль сотрудника, шифруется
     * active - показатель что аккаунт актививен
     * employee - сотрудник, привязанный к аккаунту
     * roles - роли сотрудника, определяют его превилегии
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeeEntity employee;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}