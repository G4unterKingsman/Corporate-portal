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
    private final EmployeeMapper employeeMapper;


    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employees = employeeRepo.findAll();
        return employees.stream()
                .map(e -> employeeMapper.toDto(e))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto getById(Long id){
        employeeRepo.findById(id).orElseThrow();
        return employeeMapper.toDto(employeeRepo.findById(id).orElseThrow());
    }

    @Override
    public EmployeeDto getByName(String name){
        employeeRepo.findByName(name);
        return employeeMapper.toDto(employeeRepo.findByName(name));
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity employee = employeeMapper.toEntity(employeeDto);
        employeeRepo.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto, Long id) {
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow();
        employeeMapper.updateEmployeeFromDTO(employeeDto,employee);
        employeeRepo.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }

    public EmployeeEntity findByUsername(String username) {
        return employeeRepo.findByUserAccountUsername(username);
    }
}
