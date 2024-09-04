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

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new UserAccount());
        return "registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") UserAccount user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute("ошибка, такой пользователь уже есть");
            return "/registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

}
