package com.example.mickeycj.todos.data;

import com.example.mickeycj.todos.login.LoginActivity;
import com.example.mickeycj.todos.signup.SignUpActivity;

import java.util.Collection;

/**
 * Created by mickeycj on 12/5/2560.
 */

public interface Database {

    Collection<User> getUsers();

    User getUser(String email);

    boolean isLoggedIn();

    String getCurrentEmail();

    User logIn(LoginActivity activity, String email, String password);

    void logOut();

    boolean registerUser(SignUpActivity activity, String email, String username, String password);

    void updateUser(String email, User user);
}
