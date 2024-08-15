package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.EmployeeService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;


    @Override
    public EmployeeEntity findById(Long id){
        EmployeeEntity employee = employeeRepo.findById(id).get();
        return employee;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        return employeeRepo.save(employeeEntity);
    }

}
