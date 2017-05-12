package com.example.mickeycj.todos;

import com.example.mickeycj.todos.data.Item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class ItemTest {
    Item item;

    @Before
    public void setUp() { item = new Item("Test item"); }

    @Test
    public void shouldChangeNameAfterEdit() {
        item.setName(item.getName() + " - Edited");
        assertNotEquals("Test item", item.getName());
    }

    @Test
    public void shouldMarkAsDone() {
        item.setDone(false);
        item.markAsDone();
        assertTrue(item.isDone());
    }
}
