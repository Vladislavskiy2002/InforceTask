package com.vladislavskiy.InforceTask.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/signIn")
public class SignInController {
    @GetMapping("/")
    public String getSignIn() {
        log.info("METHOD public String getSignIn()");
        return "signIn";
    }
}
