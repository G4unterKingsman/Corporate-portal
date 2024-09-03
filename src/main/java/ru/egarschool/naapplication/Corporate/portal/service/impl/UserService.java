package ru.egarschool.naapplication.Corporate.portal.service.impl;

import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;
import ru.egarschool.naapplication.Corporate.portal.entity.enums.Role;

public interface UserService {
    boolean createUser(UserAccount user);
}
