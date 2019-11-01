package com.gudigudigudi.appdemojetpack.dagger2;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;

/**
 * 提供豆沙包Baozi和统一方便面Noodle
 */
@AllArgsConstructor
@Module
public class ShangjiaAModule {

    String restaurant;

    /**
     * 提供name为"豆沙包"的Baozi
     * @return
     */
    @Provides
    public Baozi provideBaozi() {
        return new Baozi("豆沙包");
    }

    /**
     * 提供Noodle，实际提供其继承类Tongyi("统一方便面")
     * @param noodle
     * @return
     */
    @Provides
    public Noodle provideNoodle(Tongyi noodle) {
        return noodle;
    }

    @Provides
    public Kangshifu provideKangshifu() {
        return new Kangshifu();
    }

    /**
     * 提供String变量restaurant
     * @return
     */
    @Provides
    public String provideRestaurant(){
        return restaurant;
    }
}
