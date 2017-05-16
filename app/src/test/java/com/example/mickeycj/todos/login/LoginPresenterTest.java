package com.example.mickeycj.todos.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class LoginPresenterTest {

    private LoginPresenter presenter;

    @Mock
    private LoginView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(view);
    }

    @Test
    public void shouldShowEmptyEditTextsAfterStart() {
        presenter.start();
        InOrder order = inOrder(view);
        order.verify(view).clearEmailEditText();
        order.verify(view).clearPasswordEditText();
    }

    @Test
    public void shouldShowEmptyEditTextsAfterClear() {
        presenter.clearEditTexts();
        InOrder order = inOrder(view);
        order.verify(view).clearEmailEditText();
        order.verify(view).clearPasswordEditText();
    }

    @Test
    public void shouldGetEmailAndPasswordFromEditTextsAfterLogin() {
        presenter.onLoginClick();
        verify(view).getEmailFromEditText();
    }
}
