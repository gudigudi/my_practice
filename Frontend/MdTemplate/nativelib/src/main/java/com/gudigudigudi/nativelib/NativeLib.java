package com.gudigudigudi.nativelib;

public class NativeLib {
    static {
        System.loadLibrary("native-lib");
    }

    public static native String stringFromJNI();
}
