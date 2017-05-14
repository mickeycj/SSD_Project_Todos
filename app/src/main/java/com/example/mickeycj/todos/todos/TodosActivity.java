package com.example.mickeycj.todos.todos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.Todo;
import com.example.mickeycj.todos.data.User;

public class TodosActivity extends AppCompatActivity implements TodosView {

    private User user;
    private TodosPresenter presenter;

    private ArrayAdapter<Todo> todoArrayAdapter;

    private TextView usernameTextView;
    private ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        user = getIntent().getParcelableExtra("user");
        presenter = new TodosPresenter(this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        usernameTextView = (TextView) findViewById(R.id.textview_username);
        todoListView = (ListView) findViewById(R.id.listview_todo_list);
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Go to todo's details screen

            }
        });
    }

    public void onAddTodoClick(View view) {
        Intent addTodoIntent = new Intent(this, AddTodoActivity.class);
        addTodoIntent.putExtra("user", user);
        startActivityForResult(addTodoIntent, 0);
    }

    @Override
    public void setUsernameTextView() { usernameTextView.setText(user.getUsername()); }

    @Override
    public void updateTodoList() {
        todoArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getTodos());
        todoListView.setAdapter(todoArrayAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            user = data.getParcelableExtra("user");
            updateTodoList();
        }
    }
}
