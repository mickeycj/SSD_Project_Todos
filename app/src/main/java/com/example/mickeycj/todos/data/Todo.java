package com.example.mickeycj.todos.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class Todo implements Parcelable {

    private final String createdAt;
    private String name;
    private ArrayList<Item> items;

    public Todo(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setTimeZone(TimeZone.getDefault());
        this.createdAt = sdf.format(new Date());
        this.name = name;
        this.items = new ArrayList<>();
    }

    public Todo(Parcel in) {
        this.createdAt = in.readString();
        this.name = in.readString();
        this.items = new ArrayList<>();
        in.readList(this.items, Item.class.getClassLoader());
    }

    public String getCreatedAt() { return createdAt; }

    public String getName() { return name; }

    public ArrayList<Item> getItems() { return items; }

    public void setName(String name) { this.name = name; }

    public int numItems() { return items.size(); }

    public Item getItem(int index) { return items.get(index); }

    public void addItem(Item item) { items.add(item); }

    public void editItem(int index, String name) { items.get(index).setName(name); }

    public void markItemAsDone(int index) { items.get(index).markAsDone(); }

    public void deleteItem(int index) { items.remove(index); }

    public void clearItems() { items.clear(); }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdAt);
        dest.writeString(name);
        dest.writeList(items);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) { return new Todo(source); }

        @Override
        public Todo[] newArray(int size) { return new Todo[size]; }
    };
}
