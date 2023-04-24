package org.kh;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        final Deque<Message> deque = new ArrayDeque<>();

        Thread[] producers = new Thread[10];
        Thread[] consumers = new Thread[10];


        for (int i = 0; i < 10; i++) {
            producers[i] = new Thread(new Producer(deque, i));
            consumers[i] = new Thread(new Consumer(deque, i));
            producers[i].start();
            consumers[i].start();
        }

    }
}