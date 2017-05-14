package com.example.mickeycj.todos.todos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.LocalDatabase;
import com.example.mickeycj.todos.data.User;

public class EditTodoActivity extends AppCompatActivity implements EditTodoView {

    private User user;
    private int todoIndex;
    private EditTodoPresenter presenter;

    private EditText todoNameEditText;
    private CheckBox importantCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        user = getIntent().getParcelableExtra("user");
        todoIndex = getIntent().getIntExtra("todoIndex", 0);
        presenter = new EditTodoPresenter(user, todoIndex, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        todoNameEditText = (EditText) findViewById(R.id.edittext_edit_todo_name);
        importantCheckbox = (CheckBox) findViewById(R.id.checkbox_edit_todo_important);
    }

    private void updateUser(boolean deleted) {
        LocalDatabase.getInstance().updateUser(user);
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        returnedIntent.putExtra("deleted", deleted);
        setResult(RESULT_OK, returnedIntent);
        finish();
    }

    public void onEditTodoClick(View view) {
        presenter.submit();
        updateUser(false);
    }

    public void onDeleteTodoClick(View view) {
        presenter.delete();
        updateUser(true);
    }

    public void onCancelEditTodoClick(View view) { onBackPressed(); }

    @Override
    public String getTodoNameFromEditText() { return todoNameEditText.getText().toString(); }

    @Override
    public boolean isImportantCheckboxChecked() { return importantCheckbox.isChecked(); }

    @Override
    public void setTodoNameEditText() { todoNameEditText.setText(user.getTodo(todoIndex).getName()); }

    @Override
    public void setImportantCheckBox() { importantCheckbox.setChecked(user.getTodo(todoIndex).isImportant()); }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
