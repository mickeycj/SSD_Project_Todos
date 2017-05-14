package com.example.mickeycj.todos.items;

import com.example.mickeycj.todos.data.User;

/**
 * Created by user on 14/5/60.
 */

public class EditItemPresenter {

    private User user;
    private int todoIndex;
    private int itemIndex;
    private EditItemView view;

    public EditItemPresenter(User user, int todoIndex, int itemIndex, EditItemView view) {
        this.user = user;
        this.todoIndex = todoIndex;
        this.itemIndex = itemIndex;
        this.view = view;
    }

    public void start() {
        view.setItemNameEditText();
        view.setDoneCheckbox();
    }

    public void submit() { user.editItemOf(todoIndex, itemIndex, view.getItemNameFromEditText(), view.isDoneCheckboxChecked()); }

    public void delete() { user.deleteItemFrom(todoIndex, itemIndex); }
}
