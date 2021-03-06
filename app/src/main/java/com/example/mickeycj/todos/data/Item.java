package com.example.mickeycj.todos.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class Item implements Parcelable {

    private final int id;
    private String name;
    private boolean done;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.done = in.readByte() == 1;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public boolean isDone() { return done; }

    public void setName(String name) { this.name = name; }

    public void setDone(boolean done) { this.done = done; }

    public void markAsDone() { setDone(true); }

    @Override
    public String toString() { return name + ((done) ? " (" : " (NOT ") + "DONE)"; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
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
