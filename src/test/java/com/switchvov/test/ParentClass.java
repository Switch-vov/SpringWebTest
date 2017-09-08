package com.switchvov.test;

/**
 * Created by jap on 2017/6/30.
 */
public class ParentClass {
    static{
        System.out.println("ParentClass init");
    }

    public static int value = 123;

    public ParentClass() {
        System.out.println("ParentClass Constructor init");
    }

    public void sayHello(String name) {
        System.out.println("[Parent] Hello " + name);
    }
}
