package com.vladislavskiy.InforceTask.service;

import com.vladislavskiy.InforceTask.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    Boolean addUser(User user);

    User findByEmail(String email);

    void changeEmail(String newEmail, String oldEmail);

    void changeName(String newName, String oldEmail);
    Boolean changePassword(String email, String newPassword, String oldPassword);
}
