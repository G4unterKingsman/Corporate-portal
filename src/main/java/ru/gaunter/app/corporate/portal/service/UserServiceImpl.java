package ru.gaunter.app.corporate.portal.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gaunter.app.corporate.portal.entity.EmployeeEntity;
import ru.gaunter.app.corporate.portal.entity.UserAccount;
import ru.gaunter.app.corporate.portal.repository.EmployeeRepo;
import ru.gaunter.app.corporate.portal.service.intefraces.UserService;

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
