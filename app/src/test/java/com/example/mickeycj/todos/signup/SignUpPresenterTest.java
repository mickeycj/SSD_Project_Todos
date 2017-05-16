package com.example.mickeycj.todos.signup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;

/**
 * Created by mickeycj on 12/5/2560.
 */

public class SignUpPresenterTest {

    private SignUpPresenter presenter;

    @Mock
    private SignUpView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new SignUpPresenter(view);
    }

    @Test
    public void shouldShowEmptyEditTextsAfterStart() {
        presenter.start();
        InOrder order = inOrder(view);
        order.verify(view).clearEmailEditText();
        order.verify(view).clearUsernameEditText();
        order.verify(view).clearPasswordEditText();
        order.verify(view).clearConfirmPasswordEditText();
    }

    @Test
    public void shouldShowEmptyEditTextsAfterClear() {
        presenter.clearEditTexts();
        InOrder order = inOrder(view);
        order.verify(view).clearEmailEditText();
        order.verify(view).clearUsernameEditText();
        order.verify(view).clearPasswordEditText();
        order.verify(view).clearConfirmPasswordEditText();
    }
}
