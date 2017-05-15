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

    private String name;
    private boolean important;
    private ArrayList<Item> items;

    public Todo(String name, boolean important) {
        this.name = name;
        this.important = important;
        this.items = new ArrayList<>();
    }

    public Todo(Parcel in) {
        this.name = in.readString();
        this.important = in.readByte() == 1;
        this.items = new ArrayList<>();
        in.readList(this.items, Item.class.getClassLoader());
    }

    public String getName() { return name; }

    public boolean isImportant() { return important; }

    public ArrayList<Item> getItems() { return items; }

    public void setName(String name) { this.name = name; }

    public void setImportant(boolean important) { this.important = important; }

    public int numItems() { return items.size(); }

    public Item getItem(int index) { return items.get(index); }

    public void addItem(Item item) { items.add(item); }

    public void editItem(int index, String name, boolean done) { items.get(index).setName(name); items.get(index).setDone(done); }

    public void markItemAsDone(int index) { items.get(index).markAsDone(); }

    public void deleteItem(int index) { items.remove(index); }

    public void clearItems() { items.clear(); }

    @Override
    public String toString() { return name + ((important) ? " (*)" : ""); }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte)((important) ? 1 : 0));
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
