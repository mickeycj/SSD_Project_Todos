package com.example.mickeycj.todos.data;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.mickeycj.todos.login.LoginActivity;
import com.example.mickeycj.todos.signup.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mickeycj on 16/5/2560.
 */

public class OnlineDatabase extends AbstractDatabase {

    public static Database getInstance() {
        if (instance == null) {
            instance = new OnlineDatabase();
        }
        return instance;
    }

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private OnlineDatabase() {
        super();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int id = dataSnapshot.child("id").getValue(Integer.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String username = dataSnapshot.child("username").getValue(String.class);
                final User user = new User(id, username);
                DatabaseReference todoDatabaseReference = dataSnapshot.child("todos").getRef();
                todoDatabaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        int id = dataSnapshot.child("id").getValue(Integer.class);
                        String name = dataSnapshot.child("name").getValue(String.class);
                        boolean important = dataSnapshot.child("important").getValue(Boolean.class);
                        final Todo todo = new Todo(id, name, important);
                        DatabaseReference itemDatabaseReference = dataSnapshot.child("items").getRef();
                        itemDatabaseReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                int id = dataSnapshot.child("id").getValue(Integer.class);
                                String name = dataSnapshot.child("name").getValue(String.class);
                                boolean done = dataSnapshot.child("done").getValue(Boolean.class);
                                final Item item = new Item(id, name);
                                item.setDone(done);
                                todo.addItem(item);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {}

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                            @Override
                            public void onCancelled(DatabaseError databaseError) {}
                        });
                        user.addTodo(todo);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {}

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                users.put(email, user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    @Override
    public boolean isLoggedIn() { return firebaseAuth.getCurrentUser() != null; }

    @Override
    public String getCurrentEmail() { return (firebaseAuth.getCurrentUser() != null) ? firebaseAuth.getCurrentUser().getEmail() : null; }

    @Override
    public User logIn(final LoginActivity activity, final String email, final String password) {
        activity.showProgressDialog();
        final User[] user = {null};
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user[0] = getUser(email);
                    activity.goToTodoList(user[0]);
                } else {
                    new AlertDialog.Builder(activity)
                            .setTitle("Invalid Login")
                            .setMessage("You have entered invalid data.\n\nPlease enter valid login details.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    activity.clearEmailEditText();
                                    activity.clearPasswordEditText();
                                }
                            }).show();
                }
                activity.dismissProgressDialog();
            }
        });
        return user[0];
    }

    @Override
    public void logOut() { firebaseAuth.signOut(); }

    @Override
    public boolean registerUser(final SignUpActivity activity, final String email, final String username, final String password) {
        activity.showProgressDialog();
        final boolean[] successful = {false};
        if (super.registerUser(activity, email, username, password)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (successful[0] = task.isSuccessful()) {
                        databaseReference.child("" + (users.size() - 1)).setValue(new User(users.size() - 1, username));
                        databaseReference.child("" + (users.size() - 1)).child("email").setValue(email);
                        activity.returnToLoginActivity();
                        activity.finish();
                    } else {
                        users.remove(email);
                        new AlertDialog.Builder(activity)
                                .setTitle("Invalid Sign-Up")
                                .setMessage("You have entered invalid data.\n\nPlease enter valid sign-up details.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activity.clearEmailEditText();
                                        activity.clearUsernameEditText();
                                        activity.clearPasswordEditText();
                                        activity.clearConfirmPasswordEditText();
                                    }
                                }).show();
                    }
                    activity.dismissProgressDialog();
                }
            });
        }
        return successful[0];
    }

    @Override
    public void updateUser(String email, User user) {
        super.updateUser(email, user);
        databaseReference.child("" + user.getId()).setValue(user);
        databaseReference.child("" + user.getId()).child("email").setValue(email);
        if (user.numTodos() > 0) {
            for (Todo todo : user.getTodos()) {
                databaseReference.child("" + user.getId()).child("todos").child("" + todo.getId()).setValue(todo);
                if (todo.numItems() > 0) {
                    for (Item item : todo.getItems()) {
                        databaseReference.child("" + user.getId()).child("todos").child("" + todo.getId()).child("items").child("" + item.getId()).setValue(item);
                    }
                }
            }
        }
    }
}
