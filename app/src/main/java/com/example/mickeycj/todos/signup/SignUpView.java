package com.example.mickeycj.todos.signup;

/**
 * Created by mickeycj on 12/5/2560.
 */

public interface SignUpView {

    String getEmailFromEditText();

    String getUsernameFromEditText();

    String getPasswordFromEditText();

    String getPasswordConfirmationFromEditText();

    void setEmailEditText(String text);

    void setUsernameEditText(String text);

    void setPasswordEditText(String text);

    void setConfirmPasswordEditText(String text);

    void clearEmailEditText();

    void clearUsernameEditText();

    void clearPasswordEditText();

    void clearConfirmPasswordEditText();
}
