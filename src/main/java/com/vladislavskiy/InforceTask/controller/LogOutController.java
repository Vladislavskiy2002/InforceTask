package com.vladislavskiy.InforceTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogOutController {
    @GetMapping("/")
    public String logOut() {
        return "redirect:/signIn/";
    }
}
