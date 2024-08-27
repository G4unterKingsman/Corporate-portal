package ru.egarschool.naapplication.Corporate.portal.service;

import ru.egarschool.naapplication.Corporate.portal.entity.User;
import ru.egarschool.naapplication.Corporate.portal.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        userRepository.create(user);
        return null;
    }

    @Override
    public User update(User user, User newuser) {
        userRepository.update(user, newuser);
        return null;
    }

    @Override
    public User delete(User user) {
        userRepository.delete(user);
        return null;
    }
}
