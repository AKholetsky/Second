package org.kh;

public class Main {
    public static void main(String[] args) {

        final ObjectPool pool = new ObjectPool();

        Thread[] producer = new Thread[10];
        Thread[] consumer = new Thread[10];

        for (int i = 0; i < 10; i++) {
            producer[i] = new Thread(() -> {
                while(true) {
                    pool.put("Message from " + Thread.currentThread().threadId());
                }
            });
            consumer[i] = new Thread(() -> {
                while (true) {
                    System.out.println("Message " + pool.get() + " consumed in " + Thread.currentThread().threadId());
                }
            });
            producer[i].start();
            consumer[i].start();
        }

    }
}