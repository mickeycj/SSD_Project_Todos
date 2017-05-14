package com.example.mickeycj.todos.items;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.LocalDatabase;
import com.example.mickeycj.todos.data.User;

public class AddItemActivity extends AppCompatActivity implements AddItemView {

    private User user;
    private int todoIndex;
    private AddItemPresenter presenter;

    private EditText itemNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        user = getIntent().getParcelableExtra("user");
        todoIndex = getIntent().getIntExtra("todoIndex", 0);
        presenter = new AddItemPresenter(user, todoIndex, this);

        initViewHolder();

        presenter.start();
    }

    private void initViewHolder() { itemNameEditText = (EditText) findViewById(R.id.edittext_new_item_name); }

    public void onAddNewItemClick(View view) {
        presenter.submit();
        LocalDatabase.getInstance().updateUser(user);
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        setResult(RESULT_OK, returnedIntent);
        finish();
    }

    public void onCancelNewItemClick(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public String getItemNameFromEditText() { return itemNameEditText.getText().toString(); }

    @Override
    public void clearItemNameEditText() { itemNameEditText.setText(""); }
}
