package com.kinstalk.her.singlemode;

public class InnerClassSingle {

    private InnerClassSingle() {

    }

    //内部类会在使用时才加载
    public static InnerClassSingle getInstance() {
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder {
        // 类加载过程会加载静态属性，保证线程安全
        private final static InnerClassSingle INSTANCE = new InnerClassSingle();
    }
}
