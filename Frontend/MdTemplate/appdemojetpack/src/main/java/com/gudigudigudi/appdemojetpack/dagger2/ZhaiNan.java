package com.gudigudigudi.appdemojetpack.dagger2;

import javax.inject.Inject;

public class ZhaiNan {

    @Inject
    Baozi baozi;
    @Inject
    Kangshifu noodle;
//    Noodle noodle;

    @Inject
    String restaurant;

    @Inject
    public ZhaiNan() {
    }

    public String eat() {
        StringBuilder sb = new StringBuilder();
        sb.append("I eat ");
        if (baozi != null) {
            sb.append(baozi.toString());
        }
        if (noodle != null) {
            sb.append("    ");
            sb.append(noodle.toString());
        }
        sb.append(" from " + restaurant);
        return sb.toString();
    }
}
