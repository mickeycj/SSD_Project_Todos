package com.example.mickeycj.todos.data;

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
    public boolean createUser(User user) {
        if (users.get(user.getEmail()) == null) {
            return users.put(user.getEmail(), user) == null;
        }
        return false;
    }
}
