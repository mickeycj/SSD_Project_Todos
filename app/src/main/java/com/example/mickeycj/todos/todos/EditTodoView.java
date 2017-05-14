package com.example.mickeycj.todos.todos;

/**
 * Created by mickeycj on 14/5/2560.
 */

public interface EditTodoView {

    String getTodoNameFromEditText();

    boolean isImportantCheckboxChecked();

    void setTodoNameEditText();

    void setImportantCheckBox();
}
