package org.kh;

import java.util.ArrayDeque;
import java.util.Deque;

public class ObjectPool {

    private final int capacity = 100;

    final Deque<Object> objects = new ArrayDeque<>(capacity);

    public Object get() {
        while(true) {
            synchronized (objects) {
                while (objects.isEmpty()) {
                    try {
                        objects.wait(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    objects.notifyAll();
                }

                System.out.println("Polled " + objects.pollFirst() + " in " + Thread.currentThread().threadId());
            }
        }
    }

    public void put(Object obj) {
        while (true) {
            synchronized (objects) {
                while (objects.size() >= 100) {
                    try {
                        objects.wait(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    objects.notifyAll();
                }
                objects.addFirst(obj);
                System.out.println("Added " + obj);
            }
        }

    }
}
