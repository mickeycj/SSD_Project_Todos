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

    public void start() { reset(); }

    public boolean submit() {
        String name = view.getItemNameFromEditText();
        if (name != null && !name.equals("")) {
            user.editItemOf(todoIndex, itemIndex, name, view.isDoneCheckboxChecked());
            return true;
        }
        return  false;
    }

    public void delete() { user.deleteItemFrom(todoIndex, itemIndex); }

    private void reset() {
        view.setItemNameEditText();
        view.setDoneCheckbox();
    }
}
