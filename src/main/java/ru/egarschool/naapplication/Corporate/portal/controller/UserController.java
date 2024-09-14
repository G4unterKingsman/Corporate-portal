package ru.egarschool.naapplication.Corporate.portal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;
import ru.egarschool.naapplication.Corporate.portal.service.UserServiceImpl;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;



    /**
     *  проверки нет, т.к профиль может смотреть только авторизированный пользователь
     *  доступ к профилю по id юзера а не сотрудника, т.к их id совпадают (костыль)
     *  от части, сделано для безопасноти, таким образом не нужно делать проверку какой именно сотрудник смотрит профиль.
     *  это всегда будет авторизированный пользователь
     */
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "index";
    }


    @GetMapping("/login")
    public String login(@ModelAttribute("user") UserAccount user){
        return "login";
    }

    // регистрация новых пользователей через метод create в EmployeeController для админов
}
