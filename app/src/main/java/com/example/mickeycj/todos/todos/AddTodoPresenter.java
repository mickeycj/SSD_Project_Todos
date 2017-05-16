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

    public void start() {
        view.clearTodoNameEditText();
        view.setImportantCheckboxUnchecked();
    }

    public boolean submit() {
        String name = view.getTodoNameFromEditText();
        if (name != null && !name.equals("")) {
            user.addTodo(new Todo(user.numTodos(), name, view.isImportantCheckboxChecked()));
            return true;
        }
        return false;
    }
}
