package org.kh;

public class Counter {
    private int i = 0;
    private Object forSync = new Object();

    public void increment() {
        synchronized (forSync) {
            i++;
        }
    }

    public void decrement() {
        i--;
    }

    public int get() {
        return i;
    }
}
