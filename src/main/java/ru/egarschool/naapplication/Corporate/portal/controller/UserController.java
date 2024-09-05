package ru.egarschool.naapplication.Corporate.portal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;
import ru.egarschool.naapplication.Corporate.portal.service.impl.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String login(@ModelAttribute("user") UserAccount user){
        return "login";
    }


    // регистрация новых пользователей через метод create в EmployeeController для админов
    // оставить регистрацию "на будущее" ?
    // доступ к /registration закрыт
    @GetMapping("/registration")
    public String registration(Model model){
        return "обратись к админу";
    }
    @PostMapping("/registration")
    public String create(@ModelAttribute("user") UserAccount user, Model model){

        return "обратись к админу";
    }
}
