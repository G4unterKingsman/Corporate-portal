package ru.egarschool.naapplication.Corporate.portal.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;
import ru.egarschool.naapplication.Corporate.portal.exception.EmployeNotFoundException;
import ru.egarschool.naapplication.Corporate.portal.mapper.EmployeeMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.UserRepo;
import ru.egarschool.naapplication.Corporate.portal.service.intefraces.EmployeeService;

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
        return employeeMapper.toDto(employeeRepo.findById(id).orElseThrow(
                () ->  new EmployeNotFoundException("Сотрудник с идентификатором " + id + " не найден")));
    }

    @Transactional
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        String username = employeeDto.getUserAccount().getUsername();
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
        UserAccount userAccount = userRepo.findByUsername(username).orElseThrow(
                () ->  new UsernameNotFoundException("Такого username " + username + " не существует"));
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow(
                () ->  new EmployeNotFoundException("Сотрудник с идентификатором " + id + " не найден"));

        employeeMapper.updateEmployeeFromDTO(employeeDto,employee);
        employeeDto.setUserAccount(userAccount);

        employeeRepo.save(employee);
        return employeeDto;
    }



    @Override
    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }


    public String getOwnerUsername(Long id) {
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow(
                () ->  new EmployeNotFoundException("Сотрудник с индентификатором " + id + " не найден"));
        return employee.getUserAccount().getUsername();
    }
    public EmployeeEntity findEmployeeByName(String name) {
        return employeeRepo.findByName(name).orElseThrow(
                () ->  new EmployeNotFoundException("Сотрудник с именем " + name + " не найден"));
    }

}
