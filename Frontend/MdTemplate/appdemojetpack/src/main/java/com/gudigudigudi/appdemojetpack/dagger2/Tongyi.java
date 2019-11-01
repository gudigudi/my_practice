package com.gudigudigudi.appdemojetpack.dagger2;

import javax.inject.Inject;

/**
 * 统一方便面，继承Noodle基类
 */
public class Tongyi extends Noodle {

    @Inject
    public Tongyi() {
    }

    @Override
    public String toString() {
        return "统一方便面";
    }
}
