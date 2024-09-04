package ru.egarschool.naapplication.Corporate.portal.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;
import ru.egarschool.naapplication.Corporate.portal.mapper.EmployeeMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.UserRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;



    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employees = employeeRepo.findAll();
        return employees.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto getById(Long id){
        return employeeMapper.toDto(employeeRepo.findById(id).orElseThrow());
    }

    @Transactional
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        String username = employeeDto.getUserAccount().getUsername();
        if (userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует: " + username);}
        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(employeeDto.getUserAccount().getPassword()));
        user.setActive(true);
        user.setRoles(employeeDto.getUserAccount().getRoles());

        EmployeeEntity employee = employeeMapper.toEntity(employeeDto);

        user.setEmployee(employee);
        employee.setUserAccount(user);

        employeeRepo.save(employee);
        userRepo.save(user);

        return employeeMapper.toDto(employee);
    }


    @Transactional
    @Override
    public EmployeeDto update(EmployeeDto employeeDto, Long id) {
        String username = securityService.getCurrentUsername();
        UserAccount userAccount = userRepo.findByUsername(username).orElseThrow();
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow();
        employeeMapper.updateEmployeeFromDTO(employeeDto,employee);
        employeeDto.setUserAccount(userAccount);
        employeeRepo.save(employee);
        return employeeMapper.toDto(employee);
    }



    @Override
    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }


    public String getOwnerUsername(Long id) {
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow();
        return employee.getUserAccount().getUsername();
    }
}
