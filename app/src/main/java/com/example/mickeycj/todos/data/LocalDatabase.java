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
        User user1 = new User("mickey@gmail.com", "mickey", "12345678");
        Todo todo11 = new Todo("Prepare for exams", false);
        Todo todo12 = new Todo("Finish Arrowverse", true);
        user1.addTodo(todo11);
        user1.addTodo(todo12);
        user1.addItemTo(0, new Item("Read Software Specification & Design"));
        user1.addItemTo(1, new Item("Finish The Flash"));
        user1.addItemTo(1, new Item("Finish The Arrow"));
        user1.addItemTo(1, new Item("Finish Supergirl"));
        users.put(user1.getEmail(), user1);
        User user2 = new User("barry@starlabs.com", "barry", "12345678");
        Todo todo21 = new Todo("Save Iris", true);
        Todo todo22 = new Todo("Eat Big Belly Burgers", true);
        user2.addTodo(todo21);
        user2.addTodo(todo22);
        user2.addItemTo(0, new Item("Steal power cell for Speedforce Bazooka"));
        user2.addItemTo(0, new Item("Trap Savitar in the Speedforce"));
        user2.addItemTo(1, new Item("Go out with Team Flash"));
        users.put(user2.getEmail(), user2);
    }
}
