package com.kinstalk.her.wyviewpaint.proxy;

public class Test {

    public static void main(String[] args) {
        ProxyCompany proxyCompany = new ProxyCompany();
        proxyCompany.setFactory(new AFactory());
        IFactory dynamicProxy = (IFactory) proxyCompany.getDynamicProxy();
        dynamicProxy.doSome();
    }
}
