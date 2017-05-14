package com.example.mickeycj.todos.todos;

import com.example.mickeycj.todos.data.Todo;
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

public class EditTodoPresenterTest {

    private EditTodoPresenter presenter;

    @Mock
    private EditTodoView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        User user = new User("test@gmail.com", "test", "password");
        user.addTodo(new Todo("Test todo", false));
        presenter = new EditTodoPresenter(user, 0, view);
    }

    @Test
    public void shouldSetEditTextAndCheckboxAfterStart() {
        presenter.start();
        InOrder order = inOrder(view);
        order.verify(view).setTodoNameEditText();
        order.verify(view).setImportantCheckBox();
    }

    @Test
    public void shouldGetValuesFromEditTextAndCheckboxAfterSubmit() {
        presenter.submit();
        InOrder order = inOrder(view);
        order.verify(view).getTodoNameFromEditText();
        order.verify(view).isImportantCheckboxChecked();
    }
}
