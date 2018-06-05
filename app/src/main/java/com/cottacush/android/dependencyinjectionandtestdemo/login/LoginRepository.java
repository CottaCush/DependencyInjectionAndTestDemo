package com.cottacush.android.dependencyinjectionandtestdemo.login;



public interface LoginRepository {

    User getUser();

    void saveUser(User user);
}
