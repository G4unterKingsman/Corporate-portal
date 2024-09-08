package ru.egarschool.naapplication.Corporate.portal.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.service.intefraces.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final SecurityService securityService;
    private final EmployeeRepo employeeRepo;
    public UserAccount getCurrentUser() {
        String username = securityService.getCurrentUsername();
        EmployeeEntity employee = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        return employee.getUserAccount();
    }
}
