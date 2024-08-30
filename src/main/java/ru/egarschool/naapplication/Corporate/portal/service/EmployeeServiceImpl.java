package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.EmployeeMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employees = employeeRepo.findAll();

        return employees.stream()
                .map(e -> EmployeeMapper.getEmployeeDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Long id){

        employeeRepo.findById(id).orElseThrow();
        return EmployeeMapper.getEmployeeDto(employeeRepo.findById(id).orElseThrow());
    }


    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity employee = EmployeeMapper.getEmployee(employeeDto);

        employeeRepo.save(employee);
        return EmployeeMapper.getEmployeeDto(employee);
    }


    @Override
    public EmployeeDto update(EmployeeDto employeeDto,Long id) {
        EmployeeEntity employee = employeeRepo.save(EmployeeMapper.getEmployee(employeeDto));
        return EmployeeMapper.getEmployeeDto(employee);
    }

}
