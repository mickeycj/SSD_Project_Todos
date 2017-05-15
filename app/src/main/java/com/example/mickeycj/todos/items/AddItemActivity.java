package com.example.mickeycj.todos.items;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

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

    private void initViewHolder() {
        itemNameEditText = (EditText) findViewById(R.id.edittext_new_item_name);
        itemNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNewItem();
                }
                return false;
            }
        });
    }

    private void addNewItem() {
        if (presenter.submit()) {
            LocalDatabase.getInstance().updateUser(user);
            Intent returnedIntent = new Intent();
            returnedIntent.putExtra("user", user);
            setResult(RESULT_OK, returnedIntent);
            finish();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Item Name Cannot Be Empty!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearItemNameEditText();
                        }
                    }).show();
        }
    }

    public void onAddNewItemClick(View view) { addNewItem(); }

    @Override
    public String getItemNameFromEditText() { return itemNameEditText.getText().toString(); }

    @Override
    public void clearItemNameEditText() { itemNameEditText.setText(""); }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
