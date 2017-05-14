package com.example.mickeycj.todos.items;

import com.example.mickeycj.todos.data.Item;
import com.example.mickeycj.todos.data.User;

/**
 * Created by user on 14/5/60.
 */

public class AddItemPresenter {

    private User user;
    private int todoIndex;
    private AddItemView view;

    public AddItemPresenter(User user, int todoIndex, AddItemView view) {
        this.user = user;
        this.todoIndex = todoIndex;
        this.view = view;
    }

    public void start() { view.clearItemNameEditText(); }

    public void submit() { user.addItemTo(todoIndex, new Item(view.getItemNameFromEditText())); }
}
