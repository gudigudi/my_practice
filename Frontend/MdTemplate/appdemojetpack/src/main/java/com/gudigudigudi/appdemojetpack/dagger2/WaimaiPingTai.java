package com.gudigudigudi.appdemojetpack.dagger2;

import dagger.Component;

@Component(modules = {ShangjiaAModule.class, ActivityModule.class})
public interface WaimaiPingTai {
    ZhaiNan waimai();

    void zhuru(ZhaiNan zhaiNan);

    /**
     * 注入Activity
     *
     * @param daggerActivity
     */
    void inject(DaggerActivity daggerActivity);
}
