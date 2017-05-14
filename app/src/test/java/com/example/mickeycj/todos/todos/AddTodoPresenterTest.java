package com.example.mickeycj.todos.todos;

import com.example.mickeycj.todos.data.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;

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
        User user = new User("test@gmail.com", "test", "password");
        presenter = new AddTodoPresenter(user, view);
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
        InOrder order = inOrder(view);
        order.verify(view).getTodoNameFromEditText();
        order.verify(view).isImportantCheckboxChecked();
    }
}
