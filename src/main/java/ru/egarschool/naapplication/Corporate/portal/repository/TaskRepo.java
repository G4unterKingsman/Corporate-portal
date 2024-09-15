package ru.egarschool.naapplication.Corporate.portal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

}
