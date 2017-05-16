package com.example.mickeycj.todos.data;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class LocalDatabase extends AbstractDatabase {

    public static Database getInstance() {
        if (instance == null) {
            instance = new LocalDatabase();
        }
        return instance;
    }

    private LocalDatabase() {
        super();
        User user1 = new User(users.size(), "mickey@gmail.com", "mickey", "12345678");
        Todo todo11 = new Todo(user1.numTodos(), "Prepare for exams", false);
        Todo todo12 = new Todo(user1.numTodos(), "Finish Arrowverse", true);
        user1.addTodo(todo11);
        user1.addTodo(todo12);
        user1.addItemTo(0, new Item(todo11.numItems(), "Read Software Specification & Design"));
        user1.addItemTo(1, new Item(todo12.numItems(), "Finish The Flash"));
        user1.addItemTo(1, new Item(todo12.numItems(), "Finish The Arrow"));
        user1.addItemTo(1, new Item(todo12.numItems(), "Finish Supergirl"));
        users.put(user1.getEmail(), user1);
        User user2 = new User(users.size(), "barry@starlabs.com", "barry", "12345678");
        Todo todo21 = new Todo(user2.numTodos(), "Save Iris", true);
        Todo todo22 = new Todo(user2.numTodos(), "Eat Big Belly Burgers", true);
        user2.addTodo(todo21);
        user2.addTodo(todo22);
        user2.addItemTo(0, new Item(todo21.numItems(), "Steal power cell for Speedforce Bazooka"));
        user2.addItemTo(0, new Item(todo21.numItems(), "Trap Savitar in the Speedforce"));
        user2.addItemTo(1, new Item(todo22.numItems(), "Go out with Team Flash"));
        users.put(user2.getEmail(), user2);
    }
}
