package com.epam.learning.aykorenev.webservices.services;

import com.epam.learning.aykorenev.webservices.model.User;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public class UserUtils {
    public static User createUser(String firstName, String lastName, String login, String email) {
        return new User(null, firstName, lastName, login, email);
    }

    public static User createJohn(){
        return new User(null, "John", "Smith", "John-the-Smithy", "supercool@email.com");
    }


    public static List<User> createThreeUsers() {
        User user1 = new User(1L, "John", "Smith", "LoginJohn", "john@email.com");
        User user2 = new User(2L, "Bill", "Billy", "LoginBill", "bill@email.com");
        User user3 = new User(3L, "Henry", "TheGreat", "LoginHenry", "henry@email.com");
        return asList(user1, user2, user3);
    }


}
