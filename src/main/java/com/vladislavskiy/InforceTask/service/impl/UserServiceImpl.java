package com.vladislavskiy.InforceTask.service.impl;

import com.vladislavskiy.InforceTask.model.User;
import com.vladislavskiy.InforceTask.repository.UserRepository;
import com.vladislavskiy.InforceTask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
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
    @Transactional
    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }
    @Transactional
    @Override
    public void changeEmail(String newEmail, String oldEmail) {
        Optional<User> user =  Optional.ofNullable(userRepository.findByEmail(oldEmail));
        if(user.isPresent()) {
            user.get().setEmail(newEmail);
            userRepository.save(user.get());
        }
    }
    @Transactional
    @Override
    public void changeName(String newName, String oldEmail) {
        Optional<User> user =  Optional.ofNullable(userRepository.findByEmail(oldEmail));
        if(user.isPresent()) {
            user.get().setName(newName);
            userRepository.save(user.get());
        }
    }
    @Transactional
    @Override
    public void changeSurmame(String newSurname, String oldEmail) {
        Optional<User> user =  Optional.ofNullable(userRepository.findByEmail(oldEmail));
        if(user.isPresent()) {
            user.get().setSurname(newSurname);
            userRepository.save(user.get());
        }
    }
    @Transactional
    public Boolean changePassword(String email, String newPassword, String oldPassword) {
        Optional<User> user =  Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent()) {
            if (encoder.matches(oldPassword, user.get().getHashPassword())) {
                user.get().setHashPassword(encoder.encode(newPassword));
                userRepository.save(user.get());
                return true;
            } else
                return false;
        }
        else
            return false;
    }
}
