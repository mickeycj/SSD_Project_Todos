package com.example.mickeycj.todos.todos;

import com.example.mickeycj.todos.data.User;

/**
 * Created by mickeycj on 14/5/2560.
 */

public class EditTodoPresenter {

    private User user;
    private int todoIndex;
    private EditTodoView view;

    public EditTodoPresenter(User user, int todoIndex, EditTodoView view) {
        this.user = user;
        this.todoIndex = todoIndex;
        this.view = view;
    }

    public void start() {
        view.setTodoNameEditText();
        view.setImportantCheckBox();
    }

    public void submit() { user.editTodo(todoIndex, view.getTodoNameFromEditText(), view.isImportantCheckboxChecked()); }
}
