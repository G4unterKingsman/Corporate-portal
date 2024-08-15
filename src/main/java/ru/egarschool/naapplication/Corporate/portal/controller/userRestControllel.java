package ru.egarschool.naapplication.Corporate.portal.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.egarschool.naapplication.Corporate.portal.entity.User;
import ru.egarschool.naapplication.Corporate.portal.service.UserService;

import java.util.List;

@RestController
public class userRestControllel {
    private UserService userServive;


    @GetMapping("/")
    public List<User> getAllUSers(){
        return userServive.findAll();
    }
}
