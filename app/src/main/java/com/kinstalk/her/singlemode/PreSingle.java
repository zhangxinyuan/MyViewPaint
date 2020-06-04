package com.kinstalk.her.singlemode;

public class PreSingle {
    //利用类加载机制，在类加载过程中已经初始化，保证了线程安全。
    //但是会提前占用资源，造成浪费
    private static final PreSingle INSTANCE = new PreSingle();

    private PreSingle() {

    }

    public static PreSingle getInstance() {
        return INSTANCE;
    }
}
