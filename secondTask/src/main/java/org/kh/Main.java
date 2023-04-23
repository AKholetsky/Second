package org.kh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final var collection = new ArrayList<Integer>(10_000_000);

        new WriteThread(collection).start();
        new SumThread(collection).start();
        new SquareThread(collection).start();

        System.out.println("Hello world!");
    }

    static class WriteThread extends Thread {

        private final List<Integer> list;
        private final Random rand = new Random();

        public WriteThread(List<Integer> collection) {
            this.list = collection;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (list) {
                    this.list.add(rand.nextInt(10_000_000));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class SumThread extends Thread {

        private final List<Integer> list;

        public SumThread(List<Integer> collection) {
            this.list = collection;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (list) {
                    long sum = 0L;
                    for (Integer integer : list) {
                        sum += integer.longValue();
                    }
                    System.out.println("Sum = " + sum);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class SquareThread extends Thread {

        private final List<Integer> list;

        public SquareThread(List<Integer> collection) {
            this.list = collection;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (list) {
                    double sum = 0L;
                    for (Integer integer : list) {
                        sum += Math.pow(integer.doubleValue(), 2);
                    }
                    System.out.println("Square of sums = " + Math.sqrt(sum));
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}