package com.example.mickeycj.todos.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mickeycj.todos.R;
import com.example.mickeycj.todos.data.Database;
import com.example.mickeycj.todos.data.LocalDatabase;
import com.example.mickeycj.todos.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public static final Database database = LocalDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartClick(View view) {
        Intent startIntent = new Intent(this, LoginActivity.class);
        startActivity(startIntent);
    }
}
