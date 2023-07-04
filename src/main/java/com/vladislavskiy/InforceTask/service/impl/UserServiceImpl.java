package com.vladislavskiy.InforceTask.service.impl;

import com.vladislavskiy.InforceTask.model.User;
import com.vladislavskiy.InforceTask.repository.UserRepository;
import com.vladislavskiy.InforceTask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Boolean addUser(User user) {

        if(userRepository.findByEmail(user.getEmail()) == null) {
            log.info("USER IS NOT PRESENT BY EMAIl AND HAS BEEN ADDED");
            log.info(user.toString());
            userRepository.save(user);
            return true;
        }
        else {
            log.info("USER IS PRESENT BY EMAIl AND CANNOT BE ADDED");
            log.info(user.toString());
            return false;
        }
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public void changeEmail(String newEmail, String oldEmail) {
        User user =  userRepository.findByEmail(oldEmail);
        user.setEmail(newEmail);
        userRepository.save(user);
    }
    @Override
    public void changeName(String newName, String oldEmail) {
        User user = userRepository.findByEmail(oldEmail);
        user.setName(newName);
        userRepository.save(user);
    }
    public Boolean changePassword(String email, String newPassword, String oldPassword) {
        User user = userRepository.findByEmail(email);
        if(encoder.matches(oldPassword, user.getHashPassword())) {
            user.setHashPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        else
            return false;
    }
}
