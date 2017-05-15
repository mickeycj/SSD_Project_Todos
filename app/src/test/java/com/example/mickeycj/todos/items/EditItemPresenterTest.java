package com.example.mickeycj.todos.items;

import com.example.mickeycj.todos.data.Item;
import com.example.mickeycj.todos.data.Todo;
import com.example.mickeycj.todos.data.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 14/5/60.
 */

public class EditItemPresenterTest {

    private EditItemPresenter presenter;

    @Mock
    private EditItemView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        User user = new User("test@gmail.com", "test", "password");
        user.addTodo(new Todo("Test todo", false));
        user.addItemTo(0, new Item("Test item"));
        presenter = new EditItemPresenter(user, 0, 0, view);
    }

    @Test
    public void shouldSetEditTextAndCheckboxAfterStart() {
        presenter.start();
        InOrder order = inOrder(view);
        order.verify(view).setItemNameEditText();
        order.verify(view).setDoneCheckbox();
    }

    @Test
    public void shouldGetValuesFromEditTextAndCheckboxAfterSubmit() {
        presenter.submit();
        verify(view).getItemNameFromEditText();
    }
}
