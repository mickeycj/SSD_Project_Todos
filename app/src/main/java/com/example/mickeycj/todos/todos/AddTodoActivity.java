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
        todoNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNewTodo();
                }
                return false;
            }
        });
    }

    private void addNewTodo() {
        if (presenter.submit()) {
            OnlineDatabase.getInstance().updateUser(OnlineDatabase.getInstance().getCurrentEmail(), user);
            Intent returnedIntent = new Intent();
            returnedIntent.putExtra("user", user);
            setResult(RESULT_OK, returnedIntent);
            finish();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Todo Name Cannot Be Empty!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearTodoNameEditText();
                        }
                    }).show();
        }
    }

    public void onAddNewTodoClick(View view) { addNewTodo(); }

    @Override
    public String getTodoNameFromEditText() { return todoNameEditText.getText().toString(); }

    @Override
    public boolean isImportantCheckboxChecked() { return importantCheckbox.isChecked(); }

    @Override
    public void clearTodoNameEditText() { todoNameEditText.setText(""); }

    @Override
    public void setImportantCheckboxUnchecked() { importantCheckbox.setChecked(false); }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
