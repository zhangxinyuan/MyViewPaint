package com.kinstalk.her.wyviewpaint.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyCompany {

    private Object realObject;

    public void setFactory(Object o){
        realObject = o;
    }

    public Object getDynamicProxy(){
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        PreDo();
                        System.out.println("proxy:" + proxy.getClass().getCanonicalName());
                        Object invoke = method.invoke(realObject, args);
                        LastDo();
                        return invoke;
                    }
                });
    }

    private void PreDo(){
        System.out.println("123");

    }

    private void LastDo(){
        System.out.println("456");
    }
}
