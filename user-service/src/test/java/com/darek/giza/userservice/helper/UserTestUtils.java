package com.darek.giza.userservice.helper;

import com.darek.giza.userservice.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserTestUtils {

    public static final String FIRST_NAME = "Darek";
    public static final String LAST_NAME = "Giza";
    public static final String PASSWORD = "pass";
    public static final String MAIL = "darek_giza@op.pl";
    public static final String ID_11 = "11_id";
    public static final String ID_22 = "22_id";

    public static User getUser(String id, String email) {
        return User.builder().id(id)
            .email(email)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .password(PASSWORD)
            .build();
    }

    public static List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(getUser(ID_11, MAIL));
        users.add(getUser(ID_22, MAIL));
        return users;
    }

}
