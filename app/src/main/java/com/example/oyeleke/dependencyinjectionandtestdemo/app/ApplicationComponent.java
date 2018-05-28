package com.example.oyeleke.dependencyinjectionandtestdemo.app;

import com.example.oyeleke.dependencyinjectionandtestdemo.login.LoginModule;
import com.example.oyeleke.dependencyinjectionandtestdemo.login.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
}
