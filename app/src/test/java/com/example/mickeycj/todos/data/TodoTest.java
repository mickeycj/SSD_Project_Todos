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

public class TodoTest {

    private Todo todo;

    @Before
    public void setUp() { todo = new Todo(0, "Test todo", false); }

    @Test
    public void shouldChangeNameAfterEdit() {
        todo.setName(todo.getName() + " - Edited");
        assertNotEquals("Test todo", todo.getName());
    }

    @Test
    public void shouldChangeImportanceAfterEdit() {
        todo.setImportant(true);
        assertFalse(!todo.isImportant());
    }

    @Test
    public void shouldAddOneItem() {
        todo.clearItems();
        todo.addItem(new Item(0, "Test item"));
        assertEquals(1, todo.numItems());
    }

    @Test
    public void shouldAddTwoItems() {
        todo.clearItems();
        todo.addItem(new Item(0, "Test item 1"));
        todo.addItem(new Item(1, "Test item 2"));
        assertEquals(2, todo.numItems());
    }

    @Test
    public void shouldEditOnlyTheSpecifiedItem() {
        todo.clearItems();
        todo.addItem(new Item(0, "Test item 1"));
        todo.addItem(new Item(1, "Test item 2"));
        todo.addItem(new Item(2, "Test item 3"));
        todo.editItem(1, "Test item 2 - Edited", true);
        assertEquals("Test item 1", todo.getItem(0).getName());
        assertFalse(todo.getItem(0).isDone());
        assertNotEquals("Test item 2", todo.getItem(1).getName());
        assertTrue(todo.getItem(1).isDone());
        assertEquals("Test item 3", todo.getItem(2).getName());
        assertFalse(todo.getItem(2).isDone());
    }

    @Test
    public void shouldMarkTheSpecifiedItemAsDone() {
        todo.clearItems();
        todo.addItem(new Item(0, "Test item"));
        todo.markItemAsDone(0);
        assertTrue(todo.getItem(0).isDone());
    }

    @Test
    public void shouldDeleteOnlyTheSpecifiedItem() {
        todo.clearItems();
        todo.addItem(new Item(0, "Test item 1"));
        todo.addItem(new Item(1, "Test item 2"));
        todo.addItem(new Item(2, "Test item 3"));
        assertEquals(3, todo.numItems());
        todo.deleteItem(0);
        assertEquals(2, todo.numItems());
        assertNotEquals("Test item 1", todo.getItem(0));
    }
}
