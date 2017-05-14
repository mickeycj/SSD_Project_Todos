package com.example.mickeycj.todos.items;

/**
 * Created by user on 14/5/60.
 */

public interface EditItemView {

    String getItemNameFromEditText();

    boolean isDoneCheckboxChecked();

    void setItemNameEditText();

    void setDoneCheckbox();
}
