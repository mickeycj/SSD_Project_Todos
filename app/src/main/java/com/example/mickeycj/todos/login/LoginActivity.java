package com.example.mickeycj.todos.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.Database;
import com.example.mickeycj.todos.data.LocalDatabase;
import com.example.mickeycj.todos.data.User;
import com.example.mickeycj.todos.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private Database database;
    private LoginPresenter presenter;

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = LocalDatabase.getInstance();
        presenter = new LoginPresenter(database, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        emailEditText = (EditText) findViewById(R.id.edittext_login_email);
        passwordEditText = (EditText) findViewById(R.id.edittext_login_password);
    }

    public void onSignUpTabClick(View view) {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        signUpIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(signUpIntent);
    }

    public void onLoginClick(View view) {
        User user = presenter.onLoginClick();
        if (user != null) {
            // TODO Go to user's todo list page

        } else {
            clearEmailEditText();
            clearPasswordEditText();
        }
    }

    @Override
    public String getEmailFromEditText() { return emailEditText.getText().toString(); }

    @Override
    public String getPasswordFromEditText() { return passwordEditText.getText().toString(); }

    @Override
    public void clearEmailEditText() { emailEditText.setText(""); }

    @Override
    public void clearPasswordEditText() { passwordEditText.setText(""); }
}
