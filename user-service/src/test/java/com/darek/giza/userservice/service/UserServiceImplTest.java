package com.darek.giza.userservice.service;

import com.darek.giza.userservice.model.user.User;
import com.darek.giza.userservice.model.user.dto.UserRequest;
import com.darek.giza.userservice.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static com.darek.giza.userservice.helper.UserTestUtils.getUser;
import static com.darek.giza.userservice.helper.UserTestUtils.getUsers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String EMAIL = "darek_giza@op.pl";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAll() {
        List<User> users = getUsers();
        when(userRepository.getAll()).thenReturn(users);

        List<User> listUsers = userService.getAll();

        verify(userRepository, times(1)).getAll();
        assertNotNull(listUsers, "Result must not be null");
        assertEquals(2, listUsers.size(), "List size is not as expected");
        User user_0 = listUsers.get(0);
        assertEquals("11_id", user_0.getId(), "User id is not as expected");
        assertEquals("darek_giza@op.pl", user_0.getEmail(), "Email is not as expected");
        assertEquals("Darek", user_0.getFirstName(), "First name is not as expected");
        assertEquals("Giza", user_0.getLastName(), "Last name is not as expected");
        assertEquals("pass", user_0.getPassword(), "Password is not as expected");
        User user_1 = listUsers.get(1);
        assertEquals("22_id", user_1.getId(), "User id is not as expected");
        assertEquals("darek_giza@op.pl", user_1.getEmail(), "Email is not as expected");
        assertEquals("Darek", user_1.getFirstName(), "First name is not as expected");
        assertEquals("Giza", user_1.getLastName(), "Last name is not as expected");
        assertEquals("pass", user_1.getPassword(), "Password is not as expected");
    }

    @Test
    public void testGetById() {
        String id = UUID.randomUUID().toString();
        User user = getUser(id, EMAIL);
        when(userRepository.findById(id)).thenReturn(user);

        User userById = userService.getById(id);

        verify(userRepository, times(1)).findById(id);
        assertNotNull(userById, "Result must not be null");
        assertEquals(id, userById.getId(), "User id is not as expected");
        assertEquals("darek_giza@op.pl", userById.getEmail(), "Email is not as expected");
        assertEquals("Darek", userById.getFirstName(), "First name is not as expected");
        assertEquals("Giza", userById.getLastName(), "Last name is not as expected");
        assertEquals("pass", userById.getPassword(), "Password is not as expected");
    }

    @Test
    public void testUpdateById() {
        String id = UUID.randomUUID().toString();
        User user = getUser(id, EMAIL);
        when(userRepository.takById(id)).thenReturn(user);
        when(userRepository.update(user)).thenReturn(user);
        UserRequest userRequest = UserRequest.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();

        User updatedUser = userService.updateById(id, userRequest);

        verify(userRepository, times(1)).takById(id);
        verify(userRepository, times(1)).update(user);
        assertNotNull(updatedUser, "Result must not be null");
        assertEquals(id, updatedUser.getId(), "User id is not as expected");
        assertEquals("darek_giza@op.pl", updatedUser.getEmail(), "Email is not as expected");
        assertEquals(FIRST_NAME, updatedUser.getFirstName(), "First name is not as expected");
        assertEquals(LAST_NAME, updatedUser.getLastName(), "Last name is not as expected");
        assertEquals("pass", updatedUser.getPassword(), "Password is not as expected");
    }

    @Test
    public void testCreate() {
        String id = UUID.randomUUID().toString();
        User user = getUser(id, EMAIL);
        when(userRepository.create(user)).thenReturn(user);

        User createdUser = userService.create(user);

        verify(userRepository, times(1)).create(user);
        assertNotNull(createdUser, "Result must not be null");
        assertEquals(id, createdUser.getId(), "User id is not as expected");
        assertEquals("darek_giza@op.pl", createdUser.getEmail(), "Email is not as expected");
        assertEquals("Darek", createdUser.getFirstName(), "First name is not as expected");
        assertEquals("Giza", createdUser.getLastName(), "Last name is not as expected");
        assertEquals("pass", createdUser.getPassword(), "Password is not as expected");
    }

    @Test
    public void testDeleteById() {
        String id = UUID.randomUUID().toString();
        doNothing().when(userRepository).deleteById(id);

        userService.deleteById(id);

        verify(userRepository, times(1)).deleteById(id);
    }

}