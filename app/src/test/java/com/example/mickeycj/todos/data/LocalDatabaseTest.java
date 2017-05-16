package com.example.mickeycj.todos.data;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class LocalDatabaseTest {

    private Database database;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field instance = AbstractDatabase.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
        database = LocalDatabase.getInstance();
    }

    @Test
    public void shouldGetAllUsers() {
        Collection<User> users = database.getUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void shouldGetUserFromEmail() {
        User user = database.getUser("mickey@gmail.com");
        assertEquals("mickey", user.getUsername());
    }

    @Test
    public void shouldCreateNewUserAndAddToDatabase() {
        database.createUser(new User(0, "test@gmail.com", "Test", "12345678"));
        assertNotEquals(null, database.getUser("test@gmail.com"));
    }

    @Test
    public void shouldUpdateUserAfterOperations() {
        database.createUser(new User(0, "test@gmail.com", "Test", "12345678"));
        database.updateUser(new User(0, "test@gmail.com", "test", "12345678"));
        assertNotEquals(null, database.getUser("test@gmail.com"));
    }
}
