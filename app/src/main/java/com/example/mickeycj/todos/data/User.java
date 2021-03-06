package com.example.mickeycj.todos.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class User implements Parcelable {

    private final int id;
    private final String username;
    private ArrayList<Todo> todos;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
        this.todos = new ArrayList<>();
    }

    public User(Parcel in) {
        this.id = in.readInt();
        this.username = in.readString();
        this.todos = new ArrayList<>();
        in.readList(this.todos, Todo.class.getClassLoader());
    }

    public int getId() { return id; }

    public String getUsername() { return username; }

    public ArrayList<Todo> getTodos() { return todos; }

    public Todo getTodo(int index) { return todos.get(index); }

    public int numTodos() { return todos.size(); }

    public void addTodo(Todo todo) { todos.add(todo); }

    public void editTodo(int index, String name, boolean important) { todos.get(index).setName(name); todos.get(index).setImportant(important); }

    public void deleteTodo(int index) { todos.remove(index); }

    public void addItemTo(int index, Item item) { todos.get(index).addItem(item); }

    public void editItemOf(int todoIndex, int itemIndex, String name, boolean done) { todos.get(todoIndex).editItem(itemIndex, name, done); }

    public void markItemAsDone(int todoIndex, int itemIndex) { todos.get(todoIndex).markItemAsDone(itemIndex); }

    public void deleteItemFrom(int todoIndex, int itemIndex) { todos.get(todoIndex).deleteItem(itemIndex); }

    public void clearTodos() { todos.clear(); }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
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
