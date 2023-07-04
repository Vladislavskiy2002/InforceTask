package com.vladislavskiy.InforceTask.controller;

import com.vladislavskiy.InforceTask.dto.PasswordDto;
import com.vladislavskiy.InforceTask.model.User;
import com.vladislavskiy.InforceTask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    private String changeEmailCurrentUser(@Valid User user, BindingResult bindingResult, String newEmail, Principal principal, Model model){
        log.info("changeEmailCurrentUser with email: " + newEmail);
        if(!newEmail.matches("[a-zA-Z0-9]{4,15}@[a-zA-Z]{2,10}.[a-zA-Z]{2,5}")) {
            ObjectError error = new ObjectError("newEmail","email isn't correct! Example of email: name@gmail.com");
            bindingResult.addError(error);
            model.addAttribute("error", error);
            model.addAttribute("user", userService.findByEmail(principal.getName()));
            return "user-change-email";
        }
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
        userService.changeSurmame(newSurname, principal.getName());
        return "redirect:/user/";
    }
    @GetMapping("/changePassword")
    private String getFormToChangePasswordCurrentUser(Principal principal, Model model){
        return "user-change-password";
    }
    @PostMapping("/changePassword")
    private String changePasswordCurrentUser(Model model, PasswordDto passwordDto, Principal principal){
        if(!passwordDto.getNewPassword().matches("^.{4,19}$"))
        {
            return "password-not-correct";
        }
        else if(!userService.changePassword(principal.getName(), passwordDto.getNewPassword(),passwordDto.getOldPassword()))
        {
            return "password-not-changed";
        }
        else return "password-changed-successfully";
    }
}
