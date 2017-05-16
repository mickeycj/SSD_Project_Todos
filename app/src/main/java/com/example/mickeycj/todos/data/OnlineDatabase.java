package com.example.mickeycj.todos.data;

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

    private DatabaseReference databaseReference;

    private OnlineDatabase() {
        super();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int id = dataSnapshot.child("id").getValue(Integer.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String username = dataSnapshot.child("username").getValue(String.class);
                String password = dataSnapshot.child("password").getValue(String.class);
                final User user = new User(id, email, username, password);
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
                users.put(user.getEmail(), user);
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
    public boolean createUser(User user) {
        if (super.createUser(user)) {
            databaseReference.child("" + (users.size() - 1)).setValue(user);
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        super.updateUser(user);
        databaseReference.child("" + user.getId()).setValue(user);
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
