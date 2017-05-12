package com.example.mickeycj.todos.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class User implements Parcelable {

    private final String email;
    private final String username;
    private final String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(Parcel in) {
        this.email = in.readString();
        this.username = in.readString();
        this.password = in.readString();
    }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
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
