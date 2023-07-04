package com.vladislavskiy.InforceTask.controller;

import com.vladislavskiy.InforceTask.dto.PasswordDto;
import com.vladislavskiy.InforceTask.model.User;
import com.vladislavskiy.InforceTask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    private String showCurrentUser(Principal principal, Model model){
        log.info("PRINCIPAL: " + principal.getName());
        User user = userService.findByEmail(principal.getName());
        log.info(user.toString());
        model.addAttribute("user", user);
        return "user-page";
    }
    @GetMapping("/changeEmail")
    private String getFormTochangeEmailCurrentUser(Principal principal, Model model){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "user-change-email";
    }
    @PostMapping("/changeEmail")
    private String changeEmailCurrentUser(String newEmail, Principal principal, Model model){
        log.info("LLLLLLLLLLLOOOOOOOOOOGGGGGGG  " + newEmail);
        userService.changeEmail(newEmail, principal.getName());
        return "redirect:/logout/";
    }
    @GetMapping("/changeName")
    private String getFormToChangeNameCurrentUser(Principal principal, Model model){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "user-change-name";
    }
    @PostMapping("/changeName")
    private String changeNameCurrentUser(String newName, Principal principal, Model model){
        userService.changeName(newName, principal.getName());
        return "redirect:/user/";
    }
    @GetMapping("/changeSurname")
    private String getFormToChangeSurnameCurrentUser(Principal principal, Model model){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "user-change-surname";
    }
    @PostMapping("/changeSurname")
    private String changeSurnameCurrentUser(String newSurname, Principal principal, Model model){
        userService.changeName(newSurname, principal.getName());
        return "redirect:/user/";
    }
    @GetMapping("/changePassword")
    private String getFormToChangePasswordCurrentUser(Principal principal, Model model){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "user-change-password";
    }
    @PostMapping("/changePassword")
    private String changePasswordCurrentUser(PasswordDto passwordDto, Principal principal){
        if(!userService.changePassword(principal.getName(), passwordDto.getNewPassword(),passwordDto.getOldPassword()))
        {
            log.info("PASSWORD ISN'T CORRECT");
        }
        return "redirect:/user/";
    }
}
