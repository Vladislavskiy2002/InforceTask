package com.vladislavskiy.InforceTask.controller;
import com.vladislavskiy.InforceTask.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
