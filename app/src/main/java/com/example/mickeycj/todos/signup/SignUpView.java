package com.example.mickeycj.todos.signup;

/**
 * Created by mickeycj on 12/5/2560.
 */

public interface SignUpView {

    String getEmailFromEditText();

    String getUsernameFromEditText();

    String getPasswordFromEditText();

    String getPasswordConfirmationFromEditText();

    void clearEmailEditText();

    void clearUsernameEditText();

    void clearPasswordEditText();

    void clearConfirmPasswordEditText();
}
