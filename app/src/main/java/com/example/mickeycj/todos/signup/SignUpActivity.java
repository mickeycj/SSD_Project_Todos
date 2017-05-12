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

    public void onLoginTabClick(View view) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void onSignUpClick(View view) {
        if (presenter.onSignUpClick()) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } else {
            clearEmailEditText();
            clearUsernameEditText();
            clearPasswordEditText();
            clearConfirmPasswordEditText();
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
    public void clearEmailEditText() { emailEditText.setText(""); }

    @Override
    public void clearUsernameEditText() { usernameEditText.setText(""); }

    @Override
    public void clearPasswordEditText() { passwordEditText.setText(""); }

    @Override
    public void clearConfirmPasswordEditText() { confirmPasswordEditText.setText(""); }
}
