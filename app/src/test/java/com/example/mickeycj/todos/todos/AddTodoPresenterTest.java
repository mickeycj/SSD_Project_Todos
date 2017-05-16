package com.example.mickeycj.todos.todos;

import com.example.mickeycj.todos.data.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * Created by mickeycj on 14/5/2560.
 */

public class AddTodoPresenterTest {

    private AddTodoPresenter presenter;

    @Mock
    private AddTodoView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new AddTodoPresenter(new User(0, "test@gmail.com", "test", "password"), view);
    }

    @Test
    public void shouldClearEditTextAndCheckboxAfterStart() {
        presenter.start();
        InOrder order = inOrder(view);
        order.verify(view).clearTodoNameEditText();
        order.verify(view).setImportantCheckboxUnchecked();
    }

    @Test
    public void shouldGetValuesFromEditTextAndCheckboxAfterSubmit() {
        presenter.submit();
        verify(view).getTodoNameFromEditText();
    }
}
