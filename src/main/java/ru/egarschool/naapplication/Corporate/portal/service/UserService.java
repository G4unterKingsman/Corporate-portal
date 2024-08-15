package ru.egarschool.naapplication.Corporate.portal.service;

import ru.egarschool.naapplication.Corporate.portal.entity.User;
import ru.egarschool.naapplication.Corporate.portal.repository.UserRepository;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User create(User user);
    public User update(User user, User user1);
    public User delete(User user);

}
