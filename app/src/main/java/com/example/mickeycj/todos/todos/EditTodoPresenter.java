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

    public void start() { reset(); }

    public boolean submit() {
        String name = view.getTodoNameFromEditText();
        if (name != null && !name.equals("")) {
            user.editTodo(todoIndex, name, view.isImportantCheckboxChecked());
            return true;
        }
        reset();
        return false;
    }

    public void delete() { user.deleteTodo(todoIndex); }

    private void reset() {
        view.setTodoNameEditText();
        view.setImportantCheckBox();
    }
}
