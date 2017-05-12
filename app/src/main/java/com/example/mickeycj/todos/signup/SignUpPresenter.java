package com.example.mickeycj.todos.signup;

import com.example.mickeycj.todos.data.Database;
import com.example.mickeycj.todos.data.User;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class SignUpPresenter {

    private Database database;
    private SignUpView view;

    public SignUpPresenter(Database database, SignUpView view) {
        this.database = database;
        this.view = view;
    }

    public void start() {
        view.clearEmailEditText();
        view.clearUsernameEditText();
        view.clearPasswordEditText();
        view.clearConfirmPasswordEditText();
    }

    public boolean onSignUpClick() {
        String email = view.getEmailFromEditText();
        String username = view.getUsernameFromEditText();
        String password = view.getPasswordFromEditText();
        String passwordConfirmation = view.getPasswordConfirmationFromEditText();
        return password.equals(passwordConfirmation) && database.createUser(new User(email, username, password));
    }
}