package com.switchvov.test;

/**
 * Created by jap on 2017/6/30.
 */
public class ChildClass extends ParentClass {
    static {
        System.out.println("ChildClass init");
    }

    public ChildClass() {
        System.out.println("ChildClass Constructor init");
    }

    public void sayHello(String name) {
        System.out.println("[Child] Hello " + name);
    }
}
