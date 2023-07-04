package com.vladislavskiy.InforceTask.controller;

import com.vladislavskiy.InforceTask.model.User;
import com.vladislavskiy.InforceTask.repository.RoleRepository;
import com.vladislavskiy.InforceTask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@Slf4j
public class SignUpController {
    @Autowired
    UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @GetMapping("/signUp")
    public String getSignUp(Model model) {
        log.info("METHOD public String getSignUp()");
        User user = new User();
        user.setName("");
        user.setSurname("");
        user.setEmail("");
        model.addAttribute("user", user);
        return "signUp";
    }
    @PostMapping("/signUp")
    public String signUpUser(@Valid User user, BindingResult bindingResult, Model model) {
        if(user.getPassword().isEmpty() || user.getPassword().length() <= 3 || user.getPassword().length() >= 20)
        {
            ObjectError error = new ObjectError("password","password must be greatest than 3 and less than 20 symbols");
            bindingResult.addError(error);
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("user", user);
            return "signUp";
        }



        else if (bindingResult.hasFieldErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("user", user);
            return "signUp";
        }


       else if (userService.findByEmail(user.getEmail()) != null) {
            ObjectError error = new ObjectError("email","An account already exists for this email");
            bindingResult.addError(error);
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("user", user);
            return "signUp";
        }
        user.setHashPassword(encoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        userService.addUser(user);
        return "redirect:/signIn/";
    }
}
