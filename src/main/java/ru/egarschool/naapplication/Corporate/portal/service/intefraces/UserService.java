package ru.egarschool.naapplication.Corporate.portal.service.intefraces;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;

public interface UserService {

    public UserAccount getCurrentUser();
}
