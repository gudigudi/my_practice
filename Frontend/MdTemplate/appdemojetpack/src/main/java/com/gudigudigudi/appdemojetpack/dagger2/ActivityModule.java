package com.gudigudigudi.appdemojetpack.dagger2;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    public int provideActivityTest() {
        return 1234567890;
    }
}
