package com.example.mickeycj.todos.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class Todo implements Parcelable {

    private final String createdAt;
    private String name;

    public Todo(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setTimeZone(TimeZone.getDefault());
        this.createdAt = sdf.format(new Date());
        this.name = name;
    }

    public Todo(Parcel in) {
        this.createdAt = in.readString();
        this.name = in.readString();
    }

    public String getCreatedAt() { return createdAt; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdAt);
        dest.writeString(name);
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
