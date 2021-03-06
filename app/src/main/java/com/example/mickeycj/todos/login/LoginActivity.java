package com.example.mickeycj.todos.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.OnlineDatabase;
import com.example.mickeycj.todos.data.User;
import com.example.mickeycj.todos.signup.SignUpActivity;
import com.example.mickeycj.todos.todos.TodosActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;

    private EditText emailEditText;
    private EditText passwordEditText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (OnlineDatabase.getInstance().isLoggedIn()) {
            OnlineDatabase.getInstance().logOut();
        }

        presenter = new LoginPresenter(this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        emailEditText = (EditText) findViewById(R.id.edittext_login_email);
        passwordEditText = (EditText) findViewById(R.id.edittext_login_password);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signIn();
                }
                return false;
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
    }

    private void signIn() {
        User user = presenter.onLoginClick();
        if (user != null) {
            goToTodoList(user);
        }
    }

    public void goToTodoList(User user) {
        Intent userIntent = new Intent(this, TodosActivity.class);
        userIntent.putExtra("user", user);
        startActivityForResult(userIntent, 0);
    }

    public void onSignUpTabClick(View view) {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        signUpIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(signUpIntent, 0);
    }

    public void onLoginClick(View view) { signIn(); }

    @Override
    public String getEmailFromEditText() { return emailEditText.getText().toString(); }

    @Override
    public String getPasswordFromEditText() { return passwordEditText.getText().toString(); }

    @Override
    public void setEmailEditText(String text) { emailEditText.setText(text); }

    @Override
    public void setPasswordEditText(String text) { passwordEditText.setText(text); }

    @Override
    public void clearEmailEditText() { setEmailEditText(""); }

    @Override
    public void clearPasswordEditText() { setPasswordEditText(""); }

    @Override
    public void showProgressDialog() { progressDialog.show(); }

    @Override
    public void dismissProgressDialog() { progressDialog.dismiss(); }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            presenter.clearEditTexts();
        }
    }
}
