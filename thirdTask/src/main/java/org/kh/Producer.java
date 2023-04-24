package org.kh;

import java.util.Deque;

public class Producer implements Runnable {

    private final int id;
    private Deque<Message> deque;


    public Producer(final Deque<Message> deque, final int id) {
        this.deque = deque;
        this.id = id;
    }

    public void produce() {
        final var message = new Message("Produced in " + id);
        deque.add(message);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (deque) {
                while (deque.size() >= 100) {
                    try {
                        deque.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                produce();
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
