package com.example.mickeycj.todos.data;

import java.util.Collection;

/**
 * Created by mickeycj on 12/5/2560.
 */

public interface Database {

    Collection<User> getUsers();

    User getUser(String email);

    boolean createUser(User user);
}
