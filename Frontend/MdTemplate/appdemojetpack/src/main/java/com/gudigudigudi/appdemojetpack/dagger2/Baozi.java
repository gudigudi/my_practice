package com.gudigudigudi.appdemojetpack.dagger2;

import javax.inject.Inject;

public class Baozi {

    String name;

    /**
     * name默认为小笼包
     */
    @Inject
    public Baozi() {
        name = "小笼包";
    }

    public Baozi(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
