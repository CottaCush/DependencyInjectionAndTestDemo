package com.cottacush.android.dependencyinjectionandtestdemo;


import com.cottacush.android.dependencyinjectionandtestdemo.login.LoginActivityMVP;
import com.cottacush.android.dependencyinjectionandtestdemo.login.LoginActivityPresenter;
import com.cottacush.android.dependencyinjectionandtestdemo.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View  mockView;
    LoginActivityPresenter presenter;
    User user;

    //@Before ensures this method is ran before every test
    @Before
    public void setUp(){
        mockLoginModel = mock(LoginActivityMVP.Model.class);
        user = new User("Fox", "Mulder");
        mockView = mock(LoginActivityMVP.View.class);
        presenter = new LoginActivityPresenter(mockLoginModel);
        presenter.setView(mockView);
    }


    @Test
    public void loadUserFromRepositoryWhenValidUserIsPresent(){

        when(mockLoginModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, times(1)).setFirstName("Fox");
        verify(mockView,times(1)).setLastName("Mulder");
        verify(mockView, never()).showUserNotAvailable();
    }

    @Test
    public void shouldShowErrorMessageWhenUserIsNull(){

        when(mockLoginModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();

        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, never()).setFirstName("Fox");
        verify(mockView,never()).setLastName("Mulder");
        verify(mockView, times(1)).showUserNotAvailable();
    }

    @Test
    public void shouldCreateErrorMessageIfFieldAreEmpty(){
        //set up view mock
        when(mockView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockView, times(1)).getFirstName();
        verify(mockView,never()).getLastName();
        verify(mockView,times(1)).showInputError();

        //telling mock view to return value for first name and an empty last name
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockView, times(2)).getFirstName(); // called twice now
        verify(mockView,times(1)).getLastName();
        verify(mockView,times(2)).showInputError(); // called twice now
    }

    @Test
    public void shouldBeAbleToSaveAValidUser(){
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("Scully");

        presenter.loginButtonClicked();

        //called twice in the login button clicked call
        verify(mockView, times(2)).getLastName();
        verify(mockView,times(2)).getLastName();

        // making sure repository saved the user
        verify(mockLoginModel, times(1)).createUser("Dana","Scully");

        //make sure the view shows save user message
        verify(mockView, times(1)).showUserSavedMessage();
    }

}