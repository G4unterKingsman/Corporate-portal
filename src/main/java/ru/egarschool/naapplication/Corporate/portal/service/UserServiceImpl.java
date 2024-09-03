package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;
import ru.egarschool.naapplication.Corporate.portal.entity.enums.Role;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.UserRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.UserService;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;


    public UserAccount getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow();
    }

    public boolean createUser(UserAccount user){
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(user.getEmployee().getName());
        employeeRepo.save(employee);

        UserAccount newUser = new UserAccount();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setEmployee(employee);
        newUser.setRoles(Set.of(Role.ROLE_USER));

        userRepo.save(newUser);
        return true;
    }

    public void updateProfile(Long userId, String newEmployeeName) {
        UserAccount user = userRepo.findById(userId).orElseThrow();
        EmployeeEntity employee = user.getEmployee();
        employee.setName(newEmployeeName);
        employeeRepo.save(employee);
    }

}
