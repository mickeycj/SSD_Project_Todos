package com.example.mickeycj.todos.login;

/**
 * Created by mickeycj on 12/5/2560.
 */

public interface LoginView {

    String getEmailFromEditText();

    String getPasswordFromEditText();

    void setEmailEditText(String text);

    void setPasswordEditText(String text);

    void clearEmailEditText();

    void clearPasswordEditText();
}
