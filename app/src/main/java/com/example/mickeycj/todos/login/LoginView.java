package com.example.mickeycj.todos.login;

/**
 * Created by mickeycj on 12/5/2560.
 */

public interface LoginView {

    String getEmailFromEditText();

    String getPasswordFromEditText();

    void clearEmailEditText();

    void clearPasswordEditText();
}
