package com.example.mickeycj.todos.todos;

/**
 * Created by mickeycj on 14/5/2560.
 */

public interface AddTodoView {

    String getTodoNameFromEditText();

    boolean isImportantCheckboxChecked();

    void clearTodoNameEditText();

    void setImportantCheckboxUnchecked();
}
