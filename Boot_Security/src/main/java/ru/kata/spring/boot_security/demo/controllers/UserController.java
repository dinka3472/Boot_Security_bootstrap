package ru.kata.spring.boot_security.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name).orElseThrow();
        List<Role> rolesList = roleService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("rolesList", rolesList);
        return "user_page";
    }

    @GetMapping("/user_info")
    public String pageUserDetails(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        model.addAttribute("user", user);
        return "user_info";
    }
}
