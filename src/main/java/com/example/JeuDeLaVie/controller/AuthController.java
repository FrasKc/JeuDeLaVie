package com.example.JeuDeLaVie.controller;

import com.example.JeuDeLaVie.model.User;
import com.example.JeuDeLaVie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        boolean auth = userService.authenticateUser(username, password);
        if (auth) {
            return new ModelAndView("redirect:/home");
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userService.createUser(newUser);
        return new ModelAndView("redirect:/login");
    }

}
