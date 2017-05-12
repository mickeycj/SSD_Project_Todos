package com.example.mickeycj.todos.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class Item implements Parcelable {

    private String name;
    private boolean done;

    public Item(String name) {
        this.name = name;
    }

    public Item(Parcel in) {
        this.name = in.readString();
        this.done = in.readByte() == 1;
    }

    public String getName() { return name; }

    public boolean isDone() { return done; }

    public void setName(String name) { this.name = name; }

    public void setDone(boolean done) { this.done = done; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte)((done) ? 1 : 0));
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) { return new Item(source); }

        @Override
        public Item[] newArray(int size) { return new Item[size]; }
    };
}
