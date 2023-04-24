package org.kh;

import java.util.Deque;

public class Consumer implements Runnable {

    private final Deque<Message> deque;
    private final int id;

    public Consumer(final Deque<Message> deque, final int id) {
        this.deque = deque;
        this.id = id;
    }

    public void consume() {
        final var message = deque.pollFirst();
        System.out.println("consumed in " + id + " message is" + (message != null ? message.getValue() : "empty"));
    }

    @Override
    public void run() {
        while (true) {
            synchronized (deque) {
                while (deque.isEmpty()) {
                    try {
                        deque.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                consume();
                deque.notifyAll();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
