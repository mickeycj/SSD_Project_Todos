package com.example.mickeycj.todos.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class User implements Parcelable {

        private final String email;
        private final String username;
        private final String password;
        private ArrayList<Todo> todos;

    public User(String email, String username, String password) {
            this.email = email;
            this.username = username;
            this.password = password;
            this.todos = new ArrayList<>();
        }

    public User(Parcel in) {
            this.email = in.readString();
            this.username = in.readString();
            this.password = in.readString();
            this.todos = new ArrayList<>();
            in.readList(this.todos, Todo.class.getClassLoader());
        }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public ArrayList<Todo> getTodos() { return todos; }

    public Todo getTodo(int index) { return todos.get(index); }

    public void addTodo(Todo todo) { todos.add(todo); }

    public void editTodo(int index, String name) { todos.get(index).setName(name); }

    public void deleteTodo(int index) { todos.remove(index); }

    public void addItemTo(int index, Item item) { todos.get(index).addItem(item); }

    public void editItemOf(int todoIndex, int itemIndex, String name) { todos.get(todoIndex).editItem(itemIndex, name); }

    public void deleteItemFrom(int todoIndex, int itemIndex) { todos.get(todoIndex).deleteItem(itemIndex); }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeList(todos);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) { return new User(source); }

        @Override
        public User[] newArray(int size) { return new User[size]; }
    };
}
