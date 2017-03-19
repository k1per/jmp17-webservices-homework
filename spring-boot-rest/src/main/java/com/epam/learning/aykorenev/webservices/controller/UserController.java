package com.epam.learning.aykorenev.webservices.controller;

import com.epam.learning.aykorenev.webservices.model.User;
import com.epam.learning.aykorenev.webservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        return service.getAll();
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable(name = "userId") Long userId) {
        return service.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user){
        service.createUser(user);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user, @PathVariable(value = "userId") Long userId){
        user.setId(userId);
        service.updateUser(user);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(value = "userId") Long userId){
        service.deleteUserById(userId);
    }
}
