package com.example.mickeycj.todos.todos;

import com.example.mickeycj.todos.data.Todo;
import com.example.mickeycj.todos.data.User;

/**
 * Created by mickeycj on 14/5/2560.
 */

public class AddTodoPresenter {

    private User user;
    private AddTodoView view;

    public AddTodoPresenter(User user, AddTodoView view) {
        this.user = user;
        this.view = view;
    }

    public void start() { reset(); }

    public void submit() {
        String name = view.getTodoNameFromEditText();
        if (name != null && !name.equals("")) {
            user.addTodo(new Todo(name, view.isImportantCheckboxChecked()));
        } else {
            reset();
        }
    }

    private void reset() {
        view.clearTodoNameEditText();
        view.setImportantCheckboxUnchecked();
    }
}
