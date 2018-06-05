package com.cottacush.android.dependencyinjectionandtestdemo.app;

import com.cottacush.android.dependencyinjectionandtestdemo.login.LoginModule;
import com.cottacush.android.dependencyinjectionandtestdemo.login.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
}
