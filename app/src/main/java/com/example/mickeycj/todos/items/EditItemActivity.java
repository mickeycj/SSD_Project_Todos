package com.example.mickeycj.todos.items;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.LocalDatabase;
import com.example.mickeycj.todos.data.User;

public class EditItemActivity extends AppCompatActivity implements EditItemView {

    private User user;
    private int todoIndex;
    private int itemIndex;
    private EditItemPresenter presenter;

    private EditText itemNameEditText;
    private CheckBox doneCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        user = getIntent().getParcelableExtra("user");
        todoIndex = getIntent().getIntExtra("todoIndex", 0);
        itemIndex = getIntent().getIntExtra("itemIndex", 0);
        presenter = new EditItemPresenter(user, todoIndex, itemIndex, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        itemNameEditText = (EditText) findViewById(R.id.edittext_edit_item_name);
        doneCheckBox = (CheckBox) findViewById(R.id.checkbox_edit_item_done);
    }

    public void onEditItemClick(View view) {
        presenter.submit();
        LocalDatabase.getInstance().updateUser(user);
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        setResult(RESULT_OK, returnedIntent);
        finish();
    }

    public void onCancelEditTodoClick(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public String getItemNameFromEditText() { return itemNameEditText.getText().toString(); }

    @Override
    public boolean isDoneCheckboxChecked() { return doneCheckBox.isChecked(); }

    @Override
    public void setItemNameEditText() { itemNameEditText.setText(user.getTodo(todoIndex).getItem(itemIndex).getName()); }

    @Override
    public void setDoneCheckbox() { doneCheckBox.setChecked(user.getTodo(todoIndex).getItem(itemIndex).isDone()); }
}