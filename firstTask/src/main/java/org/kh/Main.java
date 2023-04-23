package org.kh;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            final long startTime = System.currentTimeMillis();
//            final Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<Integer, Integer>());
//            final Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
            final Map<Integer, Integer> map = new HashMap<Integer, Integer>();

            final WriteThread writeThread = new WriteThread(map);
            writeThread.start();
            final ReadThread readThread = new ReadThread(map);
            readThread.start();

            writeThread.join();
            readThread.join();

            System.out.println("Full sum = " + map.values().stream().mapToInt(Integer::intValue).sum());

            System.out.println("Full time = " + (System.currentTimeMillis() - startTime));
        }

    }


    static class WriteThread extends Thread {

        private Map<Integer, Integer> map;

        public WriteThread(final Map<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println("Write");
            synchronized (map) {
                for (Integer i = 0; i < 100_000; i++) {
                    map.put(i, i);
                }
            }
        }
    }

    static class ReadThread extends Thread {
        private Map<Integer, Integer> map;

        public ReadThread(final Map<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println("Read");
            long sum = 0L;
            synchronized (map) {
                for (Integer value : map.values()) {
                    sum += value.longValue();
                }
            }

            System.out.println("Sum from thread " + sum);
        }
    }
}