package com.example.mickeycj.todos.todos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;

/**
 * Created by mickeycj on 14/5/2560.
 */

public class TodosPresenterTest {

    private TodosPresenter presenter;

    @Mock
    private TodosView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new TodosPresenter(view);
    }

    @Test
    public void shouldShowUsernameAndUpdateTodoListAfterStart() {
        presenter.start();
        InOrder order = inOrder(view);
        order.verify(view).setUsernameTextView();
        order.verify(view).updateTodoList();
    }
}
