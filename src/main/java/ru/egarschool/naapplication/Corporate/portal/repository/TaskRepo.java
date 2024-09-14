package ru.egarschool.naapplication.Corporate.portal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.enums.TaskStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {


    Optional<List<TaskEntity>> findTaskEntitiesByStatus(TaskStatus status);

    Optional<TaskEntity> findTaskEntitiesById(Long id);
}
