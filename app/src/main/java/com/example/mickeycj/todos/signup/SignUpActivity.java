package com.example.mickeycj.todos.signup;

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
import com.example.mickeycj.todos.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private SignUpPresenter presenter;

    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        presenter = new SignUpPresenter(this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        emailEditText = (EditText) findViewById(R.id.edittext_signup_email);
        usernameEditText = (EditText) findViewById(R.id.edittext_signup_username);
        passwordEditText = (EditText) findViewById(R.id.edittext_signup_password);
        confirmPasswordEditText = (EditText) findViewById(R.id.edittext_signup_confirm_password);
        confirmPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signUp();
                }
                return false;
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing up...");
    }

    private void signUp() {
        if (presenter.onSignUpClick()) {
            returnToLoginActivity();
            finish();
        }
    }

    public void returnToLoginActivity() {
        Intent returnedIntent = new Intent(this, LoginActivity.class);
        returnedIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        setResult(RESULT_CANCELED, returnedIntent);
    }

    public void onLoginTabClick(View view) {
        returnToLoginActivity();
        finish();
    }

    public void onSignUpClick(View view) { signUp(); }

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

    @Override
    public void showProgressDialog() { progressDialog.show(); }

    @Override
    public void dismissProgressDialog() { progressDialog.dismiss(); }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        returnToLoginActivity();
        super.onBackPressed();
    }
}
