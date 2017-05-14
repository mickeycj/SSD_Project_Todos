package com.example.mickeycj.todos.items;

/**
 * Created by user on 14/5/60.
 */

public class ItemsPresenter {

    private ItemsView view;

    public ItemsPresenter(ItemsView view) { this.view = view; }

    public void start() {
        view.setTodoNameTextView();
        view.setTodoImportanceTextView();
        view.updateItemList();
    }
}
