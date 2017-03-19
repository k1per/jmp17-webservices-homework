package com.epam.learning.aykorenev.webservices.services;

import com.epam.learning.aykorenev.webservices.model.User;

import java.util.List;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public interface UserService {
    Long createUser(User user);
    void updateUser(User user);
    User getUserById(Long userId);
    void deleteUserById(long userId);
    List<User> getAll();
}
