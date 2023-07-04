package com.vladislavskiy.InforceTask.service;

import com.vladislavskiy.InforceTask.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    Boolean addUser(User user);

    User findByEmail(String email);

    void changeEmail(String newEmail, String oldEmail);

    void changeName(String newName, String oldEmail);
    void changeSurmame(String newSurname, String oldEmail);
    Boolean changePassword(String email, String newPassword, String oldPassword);
}
