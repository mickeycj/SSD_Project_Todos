package com.example.mickeycj.todos.login;

import com.example.mickeycj.todos.data.Database;
import com.example.mickeycj.todos.data.OnlineDatabase;
import com.example.mickeycj.todos.data.User;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class LoginPresenter {

    private LoginView view;

    public LoginPresenter(LoginView view) { this.view = view; }

    public void start() { clearEditTexts(); }

    public void clearEditTexts() {
        view.clearEmailEditText();
        view.clearPasswordEditText();
    }

    public User onLoginClick() {
        return OnlineDatabase.getInstance().logIn(((LoginActivity) view), view.getEmailFromEditText(), view.getPasswordFromEditText());
    }
}
