package com.example.mickeycj.todos.items;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.Item;
import com.example.mickeycj.todos.data.User;
import com.example.mickeycj.todos.todos.EditTodoActivity;

public class ItemsActivity extends AppCompatActivity implements ItemsView {

    private User user;
    private int todoIndex;
    private ItemsPresenter presenter;

    private ArrayAdapter<Item> itemArrayAdapter;

    private TextView todoNameTextView;
    private TextView todoImportanceTextView;
    private ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        user = getIntent().getParcelableExtra("user");
        todoIndex = getIntent().getIntExtra("todoIndex", 0);
        presenter = new ItemsPresenter(this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        todoNameTextView = (TextView) findViewById(R.id.textview_todo_name);
        todoImportanceTextView = (TextView) findViewById(R.id.textview_todo_importance);
        itemListView = (ListView) findViewById(R.id.listview_item_list);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Go to item's actions screen

            }
        });
    }

    public void onTodoNameClick(View view) {
        Intent editTodoIntent = new Intent(this, EditTodoActivity.class);
        editTodoIntent.putExtra("user", user);
        editTodoIntent.putExtra("todoIndex", todoIndex);
        startActivityForResult(editTodoIntent, 0);
    }

    public void onAddItemClick(View view) {
        Intent addItemIntent = new Intent(this, AddItemActivity.class);
        addItemIntent.putExtra("user", user);
        addItemIntent.putExtra("todoIndex", todoIndex);
        startActivityForResult(addItemIntent, 0);
    }

    @Override
    public void setTodoNameTextView() { todoNameTextView.setText(user.getTodo(todoIndex).getName()); }

    @Override
    public void setTodoImportanceTextView() { todoImportanceTextView.setText((user.getTodo(todoIndex).isImportant()) ? "*IMPORTANT*" : ""); }

    @Override
    public void updateItemList() {
        itemArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getTodo(todoIndex).getItems());
        itemListView.setAdapter(itemArrayAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            user = data.getParcelableExtra("user");
            updateItemList();
        }
    }
}
