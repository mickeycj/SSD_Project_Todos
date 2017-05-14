package com.example.mickeycj.todos.todos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.LocalDatabase;
import com.example.mickeycj.todos.data.User;

public class AddTodoActivity extends AppCompatActivity implements AddTodoView {

    private User user;
    private AddTodoPresenter presenter;

    private EditText todoNameEditText;
    private CheckBox importantCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        user = getIntent().getParcelableExtra("user");
        presenter = new AddTodoPresenter(user, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        todoNameEditText = (EditText) findViewById(R.id.edittext_new_todo_name);
        importantCheckbox = (CheckBox) findViewById(R.id.checkbox_new_todo_important);
    }

    public void onAddNewTodoClick(View view) {
        presenter.submit();
        LocalDatabase.getInstance().updateUser(user);
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        setResult(RESULT_OK, returnedIntent);
        finish();
    }

    public void onCancelNewTodoClick(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public String getTodoNameFromEditText() { return todoNameEditText.getText().toString(); }

    @Override
    public boolean isImportantCheckboxChecked() { return importantCheckbox.isChecked(); }

    @Override
    public void clearTodoNameEditText() { todoNameEditText.setText(""); }

    @Override
    public void setImportantCheckboxUnchecked() { importantCheckbox.setChecked(false); }
}
