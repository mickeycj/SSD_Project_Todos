package com.example.mickeycj.todos.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.Database;
import com.example.mickeycj.todos.data.LocalDatabase;
import com.example.mickeycj.todos.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private Database database;
    private SignUpPresenter presenter;

    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = LocalDatabase.getInstance();
        presenter = new SignUpPresenter(database, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        emailEditText = (EditText) findViewById(R.id.edittext_signup_email);
        usernameEditText = (EditText) findViewById(R.id.edittext_signup_username);
        passwordEditText = (EditText) findViewById(R.id.edittext_signup_password);
        confirmPasswordEditText = (EditText) findViewById(R.id.edittext_signup_confirm_password);
    }

    private void startLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(loginIntent);
    }

    public void onLoginTabClick(View view) {
        startLoginActivity();
    }

    public void onSignUpClick(View view) {
        if (presenter.onSignUpClick()) {
            startLoginActivity();
        }
    }

    @Override
    public String getEmailFromEditText() { return emailEditText.getText().toString(); }

    @Override
    public String getUsernameFromEditText() { return usernameEditText.getText().toString(); }

    @Override
    public String getPasswordFromEditText() { return passwordEditText.getText().toString(); }

    @Override
    public String getPasswordConfirmationFromEditText() { return confirmPasswordEditText.getText().toString(); }

    @Override
    public void setEmailEditText(String text) { emailEditText.setText(text); }

    @Override
    public void setUsernameEditText(String text) { usernameEditText.setText(text); }

    @Override
    public void setPasswordEditText(String text) { passwordEditText.setText(text); }

    @Override
    public void setConfirmPasswordEditText(String text) { confirmPasswordEditText.setText(text); }

    @Override
    public void clearEmailEditText() { setEmailEditText(""); }

    @Override
    public void clearUsernameEditText() { setUsernameEditText(""); }

    @Override
    public void clearPasswordEditText() { setPasswordEditText(""); }

    @Override
    public void clearConfirmPasswordEditText() { setConfirmPasswordEditText(""); }
}
