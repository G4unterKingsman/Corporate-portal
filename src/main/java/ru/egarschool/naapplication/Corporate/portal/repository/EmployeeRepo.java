package ru.egarschool.naapplication.Corporate.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

import java.util.Optional;


@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findByName(String name);
}
