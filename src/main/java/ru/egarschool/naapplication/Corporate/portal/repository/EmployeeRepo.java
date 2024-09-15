package ru.egarschool.naapplication.Corporate.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

import java.util.Optional;


@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByName(String name);


    /**
     * нахождение сотрудника по юзернейму, нужно для чтобы находить владельцев задач\отчётов
     */
    // @Query("SELECT e FROM EmployeeEntity e WHERE e.userAccount.username = :username")
    Optional<EmployeeEntity> findEmployeeEntityByUserAccount_Username(String username);


}
