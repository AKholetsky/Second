package org.kh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        final var map = new HashMap<Integer, Integer>();

        final var startTime = System.currentTimeMillis();

        final WriteThread writeThread = new WriteThread(map);
        writeThread.start();
        final ReadThread readThread = new ReadThread(map);
        readThread.start();



    }


    static class WriteThread extends Thread {

        private Map<Integer, Integer> map;

        public WriteThread(final Map<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println("Write");
            for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
                map.put(i, i);
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
            for (Integer value: map.values()) {
                sum += value.longValue();
            }
        }
    }
}

