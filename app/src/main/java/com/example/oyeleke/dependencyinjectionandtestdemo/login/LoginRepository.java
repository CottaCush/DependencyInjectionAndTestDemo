package com.example.oyeleke.dependencyinjectionandtestdemo.login;



public interface LoginRepository {

    User getUser();

    void saveUser(User user);
}
