package com.example.mickeycj.todos.login;

import com.example.mickeycj.todos.data.LocalDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        presenter = new LoginPresenter(LocalDatabase.getInstance(), view);
    }

    @Test
    public void shouldShowEmptyEditTextsAfterStart() {
        presenter.start();
        verify(view).clearEmailEditText();
        verify(view).clearPasswordEditText();
    }

    @Test
    public void shouldShowEmptyEditTextsAfterClear() {
        presenter.clearEditTexts();
        verify(view).clearEmailEditText();
        verify(view).clearPasswordEditText();
    }

    @Test
    public void shouldClearEditTextsAfterFailedLogin() {
        presenter.setEmailEditText("test@gmail.com");
        presenter.setPasswordEditText("password");
        presenter.onLoginClick();
        verify(view).clearEmailEditText();
        verify(view).clearPasswordEditText();
    }
}
