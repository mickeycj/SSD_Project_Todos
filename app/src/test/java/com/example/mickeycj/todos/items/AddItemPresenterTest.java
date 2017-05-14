package com.example.mickeycj.todos.items;

import com.example.mickeycj.todos.data.Todo;
import com.example.mickeycj.todos.data.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by user on 14/5/60.
 */

public class AddItemPresenterTest {

    private AddItemPresenter presenter;

    @Mock
    private AddItemView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        User user = new User("test@gmail.com", "test", "password");
        user.addTodo(new Todo("Test todo", false));
        presenter = new AddItemPresenter(user, 0, view);
    }

    @Test
    public void shouldClearEditTextAfterStart() {
        presenter.start();
        verify(view).clearItemNameEditText();
    }

    @Test
    public void shouldGetValueFromEditTextAfterSubmit() {
        presenter.submit();
        verify(view).getItemNameFromEditText();
    }
}