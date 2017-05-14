package com.example.mickeycj.todos.login;

import com.example.mickeycj.todos.data.Database;
import com.example.mickeycj.todos.data.User;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class LoginPresenter {

    private Database database;
    private LoginView view;

    public LoginPresenter(Database database, LoginView view) {
        this.database = database;
        this.view = view;
    }

    public void start() { clearEditTexts(); }

    public void clearEditTexts() {
        view.clearEmailEditText();
        view.clearPasswordEditText();
    }

    public User onLoginClick() {
        User user = database.getUser(view.getEmailFromEditText());
        if (user == null || !view.getPasswordFromEditText().equals(user.getPassword())) {
            clearEditTexts();
            return null;
        }
        return user;
    }
}
