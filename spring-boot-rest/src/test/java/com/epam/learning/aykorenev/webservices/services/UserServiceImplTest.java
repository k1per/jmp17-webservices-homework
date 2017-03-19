package com.epam.learning.aykorenev.webservices.services;

import com.epam.learning.aykorenev.webservices.exceptions.DuplicateEmailException;
import com.epam.learning.aykorenev.webservices.exceptions.DuplicateLoginException;
import com.epam.learning.aykorenev.webservices.exceptions.UserDoesNotExistsException;
import com.epam.learning.aykorenev.webservices.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public class UserServiceImplTest {

    private UserService userService;

    @Before
    public void setUp(){
        userService = new HashMapUserService();
    }

    @Test
    public void should_add_first_user_and_return_user_id_one(){
        User user = UserUtils.createUser("John", "Smith", "Johny-the-Smithy", "verycool@email.com");
        Long id = userService.createUser(user);
        assertThat(id, is(1L));
    }

    @Test
    public void should_add_second_user_and_return_value_two(){
        User user = UserUtils.createUser("John", "Smith", "Johny-the-Smithy", "verycool@email.com");
        User user2 = UserUtils.createUser("Phil", "Kill", "Phily-the-Killy", "masta@email.com");
        userService.createUser(user);
        Long id = userService.createUser(user2);
        assertThat(id, is(2L));
    }

    @Test(expected = DuplicateLoginException.class)
    public void should_throw_exception_when_same_login(){
        userService.createUser(UserUtils.createJohn());
        userService.createUser(UserUtils.createJohn());
    }

    @Test(expected = DuplicateEmailException.class)
    public void should_throw_exeption_when_same_email(){
        User john = UserUtils.createJohn();
        john.setLogin("Another");
        userService.createUser(john);
        userService.createUser(UserUtils.createJohn());
    }

    @Test
    public void should_get_user_by_id(){
        User john = UserUtils.createJohn();
        Long userId = userService.createUser(john);
        User user = userService.getUserById(userId);
        assertThat("Unexcepted user first name", user.getFirstName(), is(john.getFirstName()));
        assertThat("Unexcepted user second name", user.getLastName(), is(john.getLastName()));
        assertThat("Unexcepted user login", user.getLogin(), is(john.getLogin()));
        assertThat("Unexcepted user email", user.getEmail(), is(john.getEmail()));
    }

    @Test(expected = UserDoesNotExistsException.class)
    public void should_throw_exception_when_no_user_exists(){
        userService.getUserById(1L);
    }

    @Test(expected = UserDoesNotExistsException.class)
    public void should_delete_user_by_given_id(){
        userService.createUser(UserUtils.createJohn());
        userService.deleteUserById(1L);
        userService.getUserById(1L);
    }

    @Test
    public void should_update_user(){
        userService.createUser(UserUtils.createJohn());
        User user = UserUtils.createUser("Bill", "Billy", "FireLord", "FireLord@email.com");
        user.setId(1L);
        userService.updateUser(user);
        User updatedUser = userService.getUserById(1L);
        assertThat(updatedUser.getFirstName(), is("Bill"));
        assertThat(updatedUser.getLastName(), is("Billy"));
        assertThat(updatedUser.getLogin(), is("FireLord"));
        assertThat(updatedUser.getEmail(), is("FireLord@email.com"));

    }
}