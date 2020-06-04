package com.kinstalk.her.singlemode;

public class LazySingle {
    private static LazySingle INSTANCE = null;

    private LazySingle() {
    }

    public static LazySingle getInstance() {
        if (INSTANCE == null) {//并发情况下，会有多个线程进入，导致有多个实例。
            INSTANCE = new LazySingle();
        }
        return INSTANCE;
    }
}
