package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.TaskRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.TaskService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final EmployeeRepo employeeRepo;


    public List<TaskEntity> findAll(){
        return taskRepo.findAll();
    }

    public TaskEntity create(TaskEntity taskEntity, Long idWhoGave, Long idWhoGiven){
        if((idWhoGave != null) & (idWhoGiven != null)) {
            EmployeeEntity employeeWhoGave = employeeRepo.findById(idWhoGave).get();
            EmployeeEntity employeeWhoGiven = employeeRepo.findById(idWhoGiven).get();
            taskEntity.setWhoGaveTask(employeeWhoGave);
            taskEntity.setWhoGivenTask(employeeWhoGiven);
        }
        return taskRepo.save(taskEntity);
    }

}
