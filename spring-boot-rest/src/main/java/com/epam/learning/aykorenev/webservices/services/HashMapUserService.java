package com.epam.learning.aykorenev.webservices.services;

import com.epam.learning.aykorenev.webservices.exceptions.DuplicateEmailException;
import com.epam.learning.aykorenev.webservices.exceptions.DuplicateLoginException;
import com.epam.learning.aykorenev.webservices.exceptions.UserDoesNotExistsException;
import com.epam.learning.aykorenev.webservices.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
@Component
public class HashMapUserService implements UserService {

    // thread-unsafe
    private HashMap<Long, User> users = new HashMap<>();
    private HashMap<Long, MultipartFile> images = new HashMap<>();

    @PostConstruct
    private void populate() {
        users.put(1L, new User(1L, "John", "Snow", "Winterguy", "john@email.com"));
        users.put(2L, new User(2L, "Henry", "TheGreat", "Henry2017", "henry@email.com"));
        users.put(3L, new User(3L, "Tyrion", "Lanister", "Imp", "imp@email.com"));
    }

    @Override
    public Long createUser(User newUser) {
        checkConstraints(newUser);
        Long maxId;
        if (users.isEmpty()) maxId = 1L;
        else maxId = 1 + users.keySet().stream().mapToLong(Long::longValue).max().getAsLong();
        newUser.setId(maxId);
        users.put(maxId, newUser);
        return maxId;
    }

    private void checkConstraints(User newUser) {
        users.values().stream().forEach(user -> {
            if (user.getLogin().equals(newUser.getLogin()))
                throw new DuplicateLoginException("User with such login exists");
            if (user.getEmail().equals(newUser.getEmail()))
                throw new DuplicateEmailException("User with such email exists");
        });
    }

    @Override
    public void deleteUserById(long userId) {
        users.remove(userId);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void updateUser(User user) {
        if (!users.containsKey(user.getId()))
            throw new UserDoesNotExistsException("Could not update user, because user does not exist");
        users.put(user.getId(), user);
    }

    @Override
    public User getUserById(Long userId) {
        User user = users.get(userId);
        if (user == null) throw new UserDoesNotExistsException("No user with such id " + userId);
        return user;
    }
}
