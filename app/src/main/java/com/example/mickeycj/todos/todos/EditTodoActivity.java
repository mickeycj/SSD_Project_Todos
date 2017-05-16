package com.example.mickeycj.todos.todos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.OnlineDatabase;
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
        todoNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editTodo();
                }
                return false;
            }
        });
    }

    private void updateUser(boolean deleted) {
        OnlineDatabase.getInstance().updateUser(user);
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        returnedIntent.putExtra("deleted", deleted);
        setResult(RESULT_OK, returnedIntent);
        finish();
    }

    private void editTodo() {
        if (presenter.submit()) {
            updateUser(false);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Todo Name Cannot Be Empty!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setTodoNameEditText();
                        }
                    }).show();
        }
    }

    public void onEditTodoClick(View view) {
        editTodo();
    }

    public void onDeleteTodoClick(View view) {
        presenter.delete();
        updateUser(true);
    }

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
