package com.example.mickeycj.todos.items;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;

/**
 * Created by user on 14/5/60.
 */

public class ItemsPresenterTest {

    private ItemsPresenter presenter;

    @Mock
    private ItemsView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ItemsPresenter(view);
    }

    @Test
    public void shouldShowTodoDetailsAndUpdateItemListAfterStart() {
        presenter.start();
        InOrder order = inOrder(view);
        order.verify(view).setTodoNameTextView();
        order.verify(view).setTodoImportanceTextView();
        order.verify(view).updateItemList();
    }
}
