package com.example.mickeycj.todos.data;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class UserTest {

    private User user;

    @Before
    public void setUp() { user = new User(0, "Test"); }

    @Test
    public void shouldAddOneTodo() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo", false));
        assertEquals(1, user.numTodos());
    }

    @Test
    public void shouldAddTwoTodos() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1, "Test todo 2", false));
        assertEquals(2, user.numTodos());
    }

    @Test
    public void shouldEditOnlyTheSpecifiedTodo() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1, "Test todo 2", false));
        user.addTodo(new Todo(2, "Test todo 3", false));
        user.editTodo(1, "Test todo 2 - Edited", true);
        assertEquals("Test todo 1", user.getTodo(0).getName());
        assertFalse(user.getTodo(0).isImportant());
        assertNotEquals("Test todo 2", user.getTodo(1).getName());
        assertTrue(user.getTodo(1).isImportant());
        assertEquals("Test todo 3", user.getTodo(2).getName());
        assertFalse(user.getTodo(2).isImportant());
    }

    @Test
    public void shouldDeleteOnlyTheSpecifiedTodo() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1, "Test todo 2", false));
        user.addTodo(new Todo(2, "Test todo 3", false));
        assertEquals(3, user.numTodos());
        user.deleteTodo(0);
        assertEquals(2, user.numTodos());
        assertNotEquals("Test todo 1", user.getTodo(0));
    }

    @Test
    public void shouldAddOneItemToTheSpecifiedTodo() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1, "Test todo 2", false));
        user.addItemTo(0, new Item(0, "Test item"));
        assertEquals(1, user.getTodo(0).numItems());
    }

    @Test
    public void shouldAddTwoItemsToTheSpecifiedTodo() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1 ,"Test todo 2", false));
        user.addItemTo(0, new Item(0, "Test item 1"));
        user.addItemTo(0, new Item(1 ,"Test item 2"));
        assertEquals(2, user.getTodo(0).numItems());
    }

    @Test
    public void shouldAddTwoItemsToTwoDifferentTodos() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1, "Test todo 2", false));
        user.addItemTo(0, new Item(0, "Test item 1"));
        user.addItemTo(1, new Item(0, "Test item 2"));
        assertEquals(1, user.getTodo(0).numItems());
        assertEquals("Test item 1", user.getTodo(0).getItem(0).getName());
        assertEquals(1, user.getTodo(1).numItems());
        assertEquals("Test item 2", user.getTodo(1).getItem(0).getName());
    }

    @Test
    public void shouldEditOnlyTheSpecifiedItemFromTheSpecifiedTodo() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1, "Test todo 2", false));
        user.addItemTo(0, new Item(0, "Test item 1"));
        user.addItemTo(0, new Item(1, "Test item 2"));
        user.addItemTo(0, new Item(2 ,"Test item 3"));
        user.editItemOf(0, 1, "Test item 2 - Edited", true);
        assertEquals("Test item 1", user.getTodo(0).getItem(0).getName());
        assertFalse(user.getTodo(0).getItem(0).isDone());
        assertNotEquals("Test item 2", user.getTodo(0).getItem(1).getName());
        assertTrue(user.getTodo(0).getItem(1).isDone());
        assertEquals("Test item 3", user.getTodo(0).getItem(2).getName());
        assertFalse(user.getTodo(0).getItem(2).isDone());
    }

    @Test
    public void shouldMarkTheSpecifiedItemFromTheSpecifiedTodoAsDone() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo", false));
        user.addItemTo(0, new Item(0, "Test item"));
        user.markItemAsDone(0, 0);
        assertTrue(user.getTodo(0).getItem(0).isDone());
    }

    @Test
    public void shouldDeleteOnlyTheSpecifiedItemFromTheSpecifiedTodo() {
        user.clearTodos();
        user.addTodo(new Todo(0, "Test todo 1", false));
        user.addTodo(new Todo(1, "Test todo 2", false));
        user.addItemTo(0, new Item(0, "Test item 1"));
        user.addItemTo(0, new Item(1, "Test item 2"));
        user.addItemTo(0, new Item(2, "Test item 3"));
        assertEquals(3, user.getTodo(0).numItems());
        user.deleteItemFrom(0, 0);
        assertEquals(2, user.getTodo(0).numItems());
        assertNotEquals("Test item 1", user.getTodo(0).getItem(0).getName());
    }
}
