package com.kinstalk.her.singlemode;

public class DoubleCheckSingle {
    private static volatile DoubleCheckSingle INSTANCE;

    private DoubleCheckSingle() {

    }

    /**
     * 双重校验，需要使用volatile
     * @return
     */
    public static DoubleCheckSingle getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckSingle.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckSingle();
                }
            }
        }
        return INSTANCE;
    }
}
