package com.example.mickeycj.todos.data;

import com.example.mickeycj.todos.login.LoginActivity;
import com.example.mickeycj.todos.signup.SignUpActivity;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by mickeycj on 12/5/2560.
 */

public abstract class AbstractDatabase implements Database {

    protected static Database instance;

    protected final HashMap<String, User> users;

    protected AbstractDatabase() { users = new HashMap<>(); }

    @Override
    public Collection<User> getUsers() { return users.values(); }

    @Override
    public User getUser(String email) { return users.get(email); }

    @Override
    public boolean isLoggedIn() { return false; }

    @Override
    public String getCurrentEmail() { return null; }

    @Override
    public User logIn(LoginActivity activity, String email, String password) { return users.get(email); }

    @Override
    public void logOut() {}

    @Override
    public boolean registerUser(SignUpActivity activity, String email, String username, String password) { return users.get(email) == null && users.put(email, new User(users.size(), username)) == null; }

    @Override
    public void updateUser(String email, User user) { users.put(email, user); }
}
