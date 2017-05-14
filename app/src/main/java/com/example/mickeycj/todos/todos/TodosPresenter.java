package com.example.mickeycj.todos.todos;

import com.example.mickeycj.todos.data.User;

/**
 * Created by mickeycj on 13/5/2560.
 */

public class TodosPresenter {

    private TodosView view;

    public TodosPresenter(TodosView view) {
        this.view = view;
    }

    public void start() {
        view.setUsernameTextView();
        view.updateTodoList();
    }
}
